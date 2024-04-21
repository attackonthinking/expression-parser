package expression.exceptions;

import expression.*;

import java.util.*;

public class ExpressionParser implements TripleParser, ListParser {
    @Override
    public TripleExpression parse(String expression) throws ParsingException {
        return new ExpParser(new StringCharSource(expression)).parse();
    }

    @Override
    public ListExpression parse(String expression, List<String> variableNums) throws ParsingException {
        return new ExpParser(new StringCharSource(expression), variableNums).parse();
    }

    private static class ExpParser extends BaseParser {
        final private Map<Character, Integer> bracketBalances = new HashMap<>();
        {
            bracketBalances.put('(', 0);
            bracketBalances.put('{', 0);
            bracketBalances.put('[', 0);
        }

        final private Map<Character, Character> closeBrackets = new HashMap<>();
        {
            closeBrackets.put('(', ')');
            closeBrackets.put('{', '}');
            closeBrackets.put('[', ']');
        }
        private Map<String, Variable> variables = new HashMap<>();
        protected ExpParser(CharSource source) {
            super(source);
            this.variables = Map.of(
                    "x", new Variable("x"),
                    "y", new Variable("y"),
                    "z", new Variable("z"));
        }

        protected ExpParser(CharSource source, List<String> listVariables) {
            super(source);
            // ListIterator
            for (int num = 0; num < listVariables.size(); num++) {
                this.variables.put(listVariables.get(num), new Variable(listVariables.get(num),num));
            }
        }

        public MainExpression parse() throws ParsingException {
            MainExpression expression = parseMinMax();
            for (char bracket : bracketBalances.keySet()) {
                if (bracketBalances.get(bracket) > 0) throw error(String.format("Too many open parentheses:  %s", bracket));
            }
            if (isCloseBracket()) {
                throw error(String.format("Too many close parentheses:  %s", take()));
            }
            if (!isEOS()) throw error("Expected symbol");
            return expression;
        }
        private MainExpression parseMinMax() throws ParsingException {
            MainExpression value = parsePlusMinus();
            String command;
            while (true) {
                if (test('<') || test('>')) {
                    command = parseCommand();
                } else if (between('a', 'z')){
                      back();
                      if (!take(')')) {
                          expectWhiteSpace();
                      }
                    command = parseCommand();
                } else {
                    break;
                }
                value = calc(command, value, parsePlusMinus());
            }
            return value;
        }

        private MainExpression parsePlusMinus() throws ParsingException {
            MainExpression value = parseMulDiv();
            while (test('+') || test('-')) {
                value = calc(String.valueOf(take()), value, parseMulDiv());
            }
            return value;
        }

        private MainExpression parseMulDiv() throws ParsingException {
            MainExpression value = parseFactor();
            while (test('*') || test('/')) {
                value = calc(String.valueOf(take()), value, parseFactor());
            }
            return value;
        }

        private MainExpression parseFactor() throws ParsingException {
            MainExpression answer;
            skipWhitespaces();
            if (isOpenBracket()) {
                char bracket = take();
                bracketBalances.put(bracket, bracketBalances.get(bracket) + 1);
                MainExpression value = parseMinMax();
                expect(closeBrackets.get(bracket));
                bracketBalances.put(bracket, bracketBalances.get(bracket) - 1);
                answer = value;
            } else if (between('0', '9')) {
                answer = parseConst(false);
             } else if (take('-')) {
                if (between('0', '9')) answer = parseConst(true);
                else answer = new CheckedNegate(parseFactor());
            } else if (between('a', 'z') || test('$')) {
                String word = parseWord();
                answer = switch (word) {
                    case "l1" -> new L1(parseFactor());
                    case "t1" -> new T1(parseFactor());
                    case "low" -> new Low(parseFactor());
                    case "high" -> new High(parseFactor());
                    default -> parseVariable(word);
                };
            } else {
                throw error("Unexpected symbol");
            }
            skipWhitespaces();
            return answer;
        }

        private boolean isOpenBracket() {
            boolean ans = false;
            for (char b : bracketBalances.keySet()) ans |= test(b);
            return ans;
        }
        private boolean isCloseBracket() {
            boolean ans = false;
            for (char b : closeBrackets.values()) ans |= test(b);
            return ans;
        }
        private MainExpression parseVariable(String word) {
            if (variables.containsKey(word)) {
                return variables.get(word);
            } else {
                throw new InvalidNameVariable(word);
            }
        }

        private Const parseConst(boolean negative) throws ParsingException {
            StringBuilder num = new StringBuilder(negative ? "-" : "");
            while (between('0', '9')) {
                num.append(take());
            }
            try {
                int ans = Integer.parseInt(num.toString());
                return new Const(ans);
            } catch  (NumberFormatException e) {
                throw new OverflowNumberException("Overflow integer type", num.toString());
            }
        }

        private String parseWord() {
            StringBuilder num = new StringBuilder();
            while (test('$')|| between('a', 'z') || between('A', 'Z') || between('0', '9')) {
                num.append(take());
            }
            return num.toString();
        }

        private String parseCommand() {
            StringBuilder num = new StringBuilder();
            while (between('a', 'z') || test('<') || test('>')) {
                num.append(take());
            }
            return num.toString();
        }

        private MainExpression calc(String sign, MainExpression firstOperand, MainExpression secondOperand) throws ParsingException {
            return switch (sign) {
                case "+" -> new CheckedAdd(firstOperand, secondOperand);
                case "-" -> new CheckedSubtract(firstOperand, secondOperand);
                case "*" -> new CheckedMultiply(firstOperand, secondOperand);
                case "/" -> new CheckedDivide(firstOperand, secondOperand);
                case ">>" -> new ShiftRight(firstOperand, secondOperand);
                case "<<" -> new ShiftLeft(firstOperand, secondOperand);
                case ">>>" -> new ArithmeticShiftRight(firstOperand, secondOperand);
                case "min" -> new Min(firstOperand, secondOperand);
                case "max" -> new Max(firstOperand, secondOperand);
                default -> throw error("Unknown sign" + sign);
            };
        }
    }
}


package project2;

public class Notation {
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {

		NotationQueue<Character> queue = new NotationQueue<>(infix.length());
		NotationStack<Character> stack = new NotationStack<>(infix.length());
		char[] str = infix.toCharArray();

		try {
			for (char c : str) {
				if (c == ' ') {
					continue;
				}
				if (Character.isDigit(c)) {
					queue.enqueue(c);
					continue;
				}
				if (c == '(') {
					stack.push(c);
				}
				if (c == '*' || c == '/' || c == '+' || c == '-') {
					if (!queue.isEmpty()) {
						char top = stack.top();
						if (top == '*' || top == '/' || c == '-' && top == '-' || c == '-' && top == '+'
								|| c == '+' && top == '-' || c == '+' && top == '+') {
							queue.enqueue(stack.pop());

						}
					}
					stack.push(c);
					continue;
				}
				if (c == ')') {
					while (stack.top() != '(') {
						queue.enqueue(stack.pop());
						if (stack.top() == null) {
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}

			}
		} catch (QueueOverflowException | StackOverflowException | StackUnderflowException ignore) {
			throw new InvalidNotationFormatException();
		}
		return queue.toString();

	}


	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {

		NotationStack<String> stack = new NotationStack<>(postfix.length());

		try {
			for (int i = 0; i < postfix.length(); i++) {
				char c = postfix.charAt(i);

				if (c == ' ') {
					continue;
				}
				if (Character.isDigit(c)) {
					stack.push(Character.toString(c));
					continue;
				}
				if (c == '*' || c == '/' || c == '+' || c == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					String first = stack.pop();
					String second = stack.pop();
					String s = "(" + second + c + first + ")";
					stack.push(s);

				}
			}

		} catch (StackUnderflowException | StackOverflowException ignore) {
			throw new InvalidNotationFormatException();
		}
		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}
		return stack.toString();
	}


	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {

		NotationStack<Double> stack = new NotationStack<>(postfixExpr.length());
		char[] str = postfixExpr.toCharArray();

		try {
			for (char c : str) {
				if (c == ' ') {
					continue;
				}
				if (Character.isDigit(c) || c == '(') {
					stack.push(Double.parseDouble(Character.toString(c)));
					continue;
				}
				if (c == '*' || c == '/' || c == '+' || c == '-') {
					if (stack.size() < 2) {
						throw new InvalidNotationFormatException();
					}
					double right = stack.pop();
					double left = stack.pop();

					switch (c) {
					case '*':
						stack.push(left * right);
						break;
					case '/':
						stack.push(left / right);
						break;
					case '+':
						stack.push(left + right);
						break;
					case '-':
						stack.push(left - right);
					}

				}

			}

		} catch (StackOverflowException | StackUnderflowException ignore) {
			throw new InvalidNotationFormatException();
		}

		if (stack.size() > 1) {
			throw new InvalidNotationFormatException();
		}

		return Double.parseDouble(stack.toString());

	}


	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
		return evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
	}
}

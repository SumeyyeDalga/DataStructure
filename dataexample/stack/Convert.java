import java.util.Stack;

public class Convert {

    private static int precedence(String operator) {
        switch (operator) {
            case "+": case "-":
                return 1;
            case "*": case "/":
                return 2;
            default:
                throw new IllegalArgumentException("Geçersiz operatör: " + operator);
        }
    }

    private static boolean isDigit(String operand) {
        try {
            Double.parseDouble(operand);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String convert_to_postfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<String> operator = new Stack<>();

        String[] tokens = infix.trim().split("\\s+");

        for (String token : tokens) {
            if (isDigit(token)) {
                postfix.append(token).append(" ");
            } else {
                while (!operator.isEmpty() && precedence(token) <= precedence(operator.peek())) {
                    postfix.append(operator.pop()).append(" ");
                }
                operator.push(token);
            }
        }

        while (!operator.isEmpty()) {
            postfix.append(operator.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    public static void main(String[] args) {
        String infix = "3 * 4 + 2";
        System.out.println("Postfix: " + convert_to_postfix(infix)); // Beklenen çıktı: 3 4 2 * +
    }
}

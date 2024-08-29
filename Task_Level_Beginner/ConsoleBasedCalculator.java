import java.util.Scanner;

public class ConsoleBasedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        while (isActive) {
            System.out.println("==============Console-based Calculator==============");
            System.out.println("1. Basic Arithmetic Operations");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int selection = scanner.nextInt();
            scanner.nextLine();  // To clear the newline character

            switch (selection) {
                case 1:
                    handleArithmeticOperations(scanner);
                    break;
                case 2:
                    handleScientificCalculations(scanner);
                    break;
                case 3:
                    handleUnitConversions(scanner);
                    break;
                case 4:
                    isActive = false;
                    System.out.println("Terminating the calculator. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid choice.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void handleArithmeticOperations(Scanner scanner) {
        System.out.print("Input an arithmetic expression (e.g., 5+5 or 6*7): ");
        String expression = scanner.nextLine();

        try {
            double outcome = calculateExpression(expression);
            System.out.println("Outcome: " + outcome);
        } catch (Exception e) {
            System.out.println("Error: Invalid expression. Please try again.");
        }
    }

    private static double calculateExpression(String expression) throws Exception {
        // Eliminate spaces in the expression
        expression = expression.replaceAll("\\s+", "");

        double result = 0.0;
        char operator = ' ';
        String[] components = expression.split("(?<=[-+*/])|(?=[-+*/])");  // Splitting based on operators

        if (components.length < 3) {
            throw new Exception("Error: Incomplete expression");
        }

        // Parsing the first number
        double firstOperand = Double.parseDouble(components[0]);
        operator = components[1].charAt(0);
        double secondOperand = Double.parseDouble(components[2]);

        // Perform the respective calculation
        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    throw new ArithmeticException("Error: Division by zero");
                }
                break;
            default:
                throw new Exception("Error: Unknown operator");
        }

        return result;
    }

    private static void handleScientificCalculations(Scanner scanner) {
        System.out.println("Choose a scientific calculation:");
        System.out.println("1. Square Root");
        System.out.println("2. Exponentiation");
        System.out.println("3. Trigonometric Functions");
        System.out.println("4. Logarithm");
        System.out.println("5. Power");
        System.out.println("6. Factorial");
        System.out.println("7. Absolute Value");
        System.out.println("8. Natural Logarithm (ln)");
        System.out.println("9. Exponential (e^x)");
        System.out.print("Select an option: ");
        int selection = scanner.nextInt();

        switch (selection) {
            case 1:
                System.out.print("Enter the number: ");
                double number = scanner.nextDouble();
                if (number >= 0) {
                    double sqrtResult = Math.sqrt(number);
                    System.out.println("Square Root: " + sqrtResult);
                } else {
                    System.out.println("Error: Cannot calculate square root of a negative number.");
                }
                break;
            case 2:
                System.out.print("Enter the base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter the exponent: ");
                double exponent = scanner.nextDouble();
                double exponentiationResult = Math.pow(base, exponent);
                System.out.println("Result: " + exponentiationResult);
                break;
            case 3:
                System.out.println("Select trigonometric function:");
                System.out.println("1. Sine");
                System.out.println("2. Cosine");
                System.out.println("3. Tangent");
                System.out.print("Select an option: ");
                int trigChoice = scanner.nextInt();
                System.out.print("Enter the angle in degrees: ");
                double angle = scanner.nextDouble();
                double radians = Math.toRadians(angle);
                switch (trigChoice) {
                    case 1:
                        System.out.println("Sine: " + Math.sin(radians));
                        break;
                    case 2:
                        System.out.println("Cosine: " + Math.cos(radians));
                        break;
                    case 3:
                        System.out.println("Tangent: " + Math.tan(radians));
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
                break;
            case 4:
                System.out.print("Enter the number: ");
                double logInput = scanner.nextDouble();
                if (logInput > 0) {
                    System.out.println("Logarithm (base 10): " + Math.log10(logInput));
                } else {
                    System.out.println("Error: Logarithm undefined for non-positive numbers.");
                }
                break;
            case 5:
                System.out.print("Enter the base: ");
                double baseValue = scanner.nextDouble();
                System.out.print("Enter the exponent: ");
                double expValue = scanner.nextDouble();
                System.out.println("Power: " + Math.pow(baseValue, expValue));
                break;
            case 6:
                System.out.print("Enter a non-negative integer: ");
                int factValue = scanner.nextInt();
                if (factValue >= 0) {
                    System.out.println("Factorial: " + computeFactorial(factValue));
                } else {
                    System.out.println("Error: Factorial of a negative number is undefined.");
                }
                break;
            case 7:
                System.out.print("Enter the number: ");
                double absValue = scanner.nextDouble();
                System.out.println("Absolute Value: " + Math.abs(absValue));
                break;
            case 8:
                System.out.print("Enter the number: ");
                double lnValue = scanner.nextDouble();
                if (lnValue > 0) {
                    System.out.println("Natural Logarithm (ln): " + Math.log(lnValue));
                } else {
                    System.out.println("Error: Natural logarithm undefined for non-positive numbers.");
                }
                break;
            case 9:
                System.out.print("Enter the exponent: ");
                double expInput = scanner.nextDouble();
                System.out.println("Exponential (e^x): " + Math.exp(expInput));
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    private static long computeFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * computeFactorial(n - 1);
    }

    private static void handleUnitConversions(Scanner scanner) {
        System.out.println("Choose a unit conversion:");
        System.out.println("1. Temperature (Celsius to Fahrenheit)");
        System.out.println("2. Temperature (Fahrenheit to Celsius)");
        System.out.println("3. Length (Meters to Feet)");
        System.out.println("4. Length (Feet to Meters)");
        System.out.println("5. Weight (Kilograms to Pounds)");
        System.out.println("6. Weight (Pounds to Kilograms)");
        System.out.println("7. Volume (Liters to Gallons)");
        System.out.println("8. Volume (Gallons to Liters)");
        System.out.println("9. Currency (USD to EUR)");
        System.out.println("10. Currency (EUR to USD)");
        System.out.print("Select an option: ");
        int selection = scanner.nextInt();

        switch (selection) {
            case 1:
                System.out.print("Enter temperature in Celsius: ");
                double celsiusValue = scanner.nextDouble();
                double fahrenheitValue = (celsiusValue * 9 / 5) + 32;
                System.out.println("Temperature in Fahrenheit: " + fahrenheitValue);
                break;
            case 2:
                System.out.print("Enter temperature in Fahrenheit: ");
                double fahrenheitInput = scanner.nextDouble();
                double celsiusResult = (fahrenheitInput - 32) * 5 / 9;
                System.out.println("Temperature in Celsius: " + celsiusResult);
                break;
            case 3:
                System.out.print("Enter length in meters: ");
                double metersValue = scanner.nextDouble();
                double feetValue = metersValue * 3.28084;
                System.out.println("Length in Feet: " + feetValue);
                break;
            case 4:
                System.out.print("Enter length in feet: ");
                double feetInput = scanner.nextDouble();
                double metersResult = feetInput / 3.28084;
                System.out.println("Length in Meters: " + metersResult);
                break;
            case 5:
                System.out.print("Enter weight in kilograms: ");
                double kgValue = scanner.nextDouble();
                double poundsValue = kgValue * 2.20462;
                System.out.println("Weight in Pounds: " + poundsValue);
                break;
            case 6:
                System.out.print("Enter weight in pounds: ");
                double poundsInput = scanner.nextDouble();
                double kilogramsResult = poundsInput / 2.20462;
                System.out.println("Weight in Kilograms: " + kilogramsResult);
                break;
            case 7:
                System.out.print("Enter volume in liters: ");
                double litersValue = scanner.nextDouble();
                double gallonsValue = litersValue * 0.264172;
                System.out.println("Volume in Gallons: " + gallonsValue);
                break;
            case 8:
                System.out.print("Enter volume in gallons: ");
                double gallonsInput = scanner.nextDouble();
                double litersResult = gallonsInput / 0.264172;
                System.out.println("Volume in Liters: " + litersResult);
                break;
            case 9:
                System.out.print("Enter amount in USD: ");
                double usdValue = scanner.nextDouble();
                double eurValue = usdValue * 0.85; // Example conversion rate
                System.out.println("Amount in EUR: " + eurValue);
                break;
            case 10:
                System.out.print("Enter amount in EUR: ");
                double euroValue = scanner.nextDouble();
                double usdResult = euroValue * 1.18; // Example conversion rate
                System.out.println("Amount in USD: " + usdResult);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}

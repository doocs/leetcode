class Solution {
public:
    // Define an operation function that performs mathematical operations based on the operator
    int operate(int b, char ch, int a) {
        // Note the order of ab
        switch (ch) {
        case '+':
            return a + b; // Addition
        case '-':
            return a - b; // Subtraction
        case '*':
            return a * b; // Multiplication
        case '/':
            return a / b; // Division
        default:
            break;
        }
        return 0; // Default return 0, handle invalid operators
    }

    // Calculate the value of the string expression
    int calculate(string s) {
        int preority[250]; // Operator precedence array
        preority['+'] = 1;
        preority['-'] = 1;
        preority['*'] = 2;
        preority['/'] = 2;
        preority['('] = 0;
        preority[')'] = 0;

        stack<char> op; // Operator stack
        stack<int> num; // Operand stack
        int stringsize = s.size(); // Length of the string
        int i = 0;
        char ch;

        // Traverse the string
        for (; i < stringsize; i++) {
            ch = s[i];
            if (ch == ' ') {
                continue; // Skip spaces
            }
            if (ch >= '0' && ch <= '9') {
                int realnum = ch - '0'; // Convert character to number
                // Handle multi-digit numbers
                while (s[i + 1] >= '0' && s[i + 1] <= '9') {
                    i++;
                    realnum *= 10;
                    realnum += s[i] - '0';
                }
                num.push(realnum); // Push the number onto the stack
            } else {
                // Handle operators
                if (op.empty() || ch == '(' || preority[ch] > preority[op.top()]) {
                    // Special case, handle the first character being '-' or '+'
                    if (num.empty() && (ch == '-' || ch == '+')) {
                        num.push(0);
                    }
                    op.push(ch); // Push the operator onto the stack
                    // Handle expressions inside parentheses
                    if (ch == '(') {
                        int j = i;
                        while (j + 1 < stringsize) {
                            // Preprocess the first operator inside the parentheses
                            if (s[j + 1] == '-' || s[j + 1] == '+') {
                                num.push(0);
                            }
                            if (s[j + 1] != ' ') {
                                break;
                            }
                            j++;
                        }
                    }
                } else if (ch == ')') {
                    // Handle right parentheses
                    char ch2 = ')';
                    ch2 = op.top();
                    op.pop();
                    while (ch2 != '(') {
                        int a = num.top();
                        num.pop();
                        int b = num.top();
                        num.pop();
                        num.push(operate(a, ch2, b)); // Calculate and push the result
                        ch2 = op.top();
                        op.pop();
                    }
                } else if (preority[ch] <= preority[op.top()]) {
                    // Handle cases where the precedence is less than or equal to the top of the stack
                    char ch2;
                    ch2 = op.top();
                    while (!op.empty() && preority[ch] <= preority[op.top()] && ch2 != '(') {
                        op.pop();
                        int a = num.top();
                        num.pop();
                        int b = num.top();
                        num.pop();
                        num.push(operate(a, ch2, b)); // Calculate and push the result
                        if (!op.empty()) {
                            ch2 = op.top();
                        } else {
                            break;
                        }
                    }
                    op.push(ch); // Push the current operator onto the stack
                }
            }
        }

        // Handle the remaining expressions in the stack
        while (!op.empty()) {
            ch = op.top();
            op.pop();
            int a = num.top();
            num.pop();
            int b = num.top();
            num.pop();
            num.push(operate(a, ch, b)); // Calculate and push the result
        }

        return num.top(); // Return the final result
    }
};

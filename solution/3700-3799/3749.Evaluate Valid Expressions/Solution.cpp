class Solution {
public:
    long long evaluateExpression(string expression) {
        auto parse = [&](this auto&& parse, int i) -> pair<long long, int> {
            if (isdigit(expression[i]) || expression[i] == '-') {
                int j = i;
                if (expression[j] == '-') {
                    j++;
                }
                while (j < expression.size() && isdigit(expression[j])) {
                    j++;
                }
                long long num = stoll(expression.substr(i, j - i));
                return {num, j};
            }

            int j = i;
            while (expression[j] != '(') {
                j++;
            }
            string op = expression.substr(i, j - i);
            j++;

            auto [val1, next_j1] = parse(j);
            j = next_j1 + 1;

            auto [val2, next_j2] = parse(j);
            j = next_j2 + 1;

            long long res = 0;
            if (op == "add") {
                res = val1 + val2;
            } else if (op == "sub") {
                res = val1 - val2;
            } else if (op == "mul") {
                res = val1 * val2;
            } else if (op == "div") {
                res = val1 / val2;
            }

            return {res, j};
        };

        return parse(0).first;
    }
};

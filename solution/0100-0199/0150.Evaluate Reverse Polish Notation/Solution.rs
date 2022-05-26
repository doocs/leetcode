impl Solution {
    pub fn eval_rpn(tokens: Vec<String>) -> i32 {
        let mut stack = vec![];
        for token in tokens {
            match token.parse() {
                Ok(num) => stack.push(num),
                Err(_) => {
                    let a = stack.pop().unwrap();
                    let b = stack.pop().unwrap();
                    stack.push(match token.as_str() {
                        "+" => b + a,
                        "-" => b - a,
                        "*" => b * a,
                        "/" => b / a,
                        _ => 0,
                    })
                }
            }
        }
        stack[0]
    }
}

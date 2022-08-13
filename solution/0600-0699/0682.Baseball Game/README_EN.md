# [682. Baseball Game](https://leetcode.com/problems/baseball-game)

[中文文档](/solution/0600-0699/0682.Baseball%20Game/README.md)

## Description

<p>You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds&#39; scores.</p>

<p>At the beginning of the game, you start with an empty record. You are given a list of strings <code>ops</code>, where <code>ops[i]</code> is the <code>i<sup>th</sup></code> operation you must apply to the record and is one of the following:</p>

<ol>
	<li>An integer <code>x</code> - Record a new score of <code>x</code>.</li>
	<li><code>&quot;+&quot;</code> - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.</li>
	<li><code>&quot;D&quot;</code> - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.</li>
	<li><code>&quot;C&quot;</code> - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.</li>
</ol>

<p>Return <em>the sum of all the scores on the record</em>. The test cases are generated so that the answer fits in a 32-bit integer.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> ops = [&quot;5&quot;,&quot;2&quot;,&quot;C&quot;,&quot;D&quot;,&quot;+&quot;]
<strong>Output:</strong> 30
<strong>Explanation:</strong>
&quot;5&quot; - Add 5 to the record, record is now [5].
&quot;2&quot; - Add 2 to the record, record is now [5, 2].
&quot;C&quot; - Invalidate and remove the previous score, record is now [5].
&quot;D&quot; - Add 2 * 5 = 10 to the record, record is now [5, 10].
&quot;+&quot; - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
The total sum is 5 + 10 + 15 = 30.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> ops = [&quot;5&quot;,&quot;-2&quot;,&quot;4&quot;,&quot;C&quot;,&quot;D&quot;,&quot;9&quot;,&quot;+&quot;,&quot;+&quot;]
<strong>Output:</strong> 27
<strong>Explanation:</strong>
&quot;5&quot; - Add 5 to the record, record is now [5].
&quot;-2&quot; - Add -2 to the record, record is now [5, -2].
&quot;4&quot; - Add 4 to the record, record is now [5, -2, 4].
&quot;C&quot; - Invalidate and remove the previous score, record is now [5, -2].
&quot;D&quot; - Add 2 * -2 = -4 to the record, record is now [5, -2, -4].
&quot;9&quot; - Add 9 to the record, record is now [5, -2, -4, 9].
&quot;+&quot; - Add -4 + 9 = 5 to the record, record is now [5, -2, -4, 9, 5].
&quot;+&quot; - Add 9 + 5 = 14 to the record, record is now [5, -2, -4, 9, 5, 14].
The total sum is 5 + -2 + -4 + 9 + 5 + 14 = 27.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> ops = [&quot;1&quot;,&quot;C&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong>
&quot;1&quot; - Add 1 to the record, record is now [1].
&quot;C&quot; - Invalidate and remove the previous score, record is now [].
Since the record is empty, the total sum is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ops.length &lt;= 1000</code></li>
	<li><code>ops[i]</code> is <code>&quot;C&quot;</code>, <code>&quot;D&quot;</code>, <code>&quot;+&quot;</code>, or a string representing an integer in the range <code>[-3 * 10<sup>4</sup>, 3 * 10<sup>4</sup>]</code>.</li>
	<li>For operation <code>&quot;+&quot;</code>, there will always be at least two previous scores on the record.</li>
	<li>For operations <code>&quot;C&quot;</code> and <code>&quot;D&quot;</code>, there will always be at least one previous score on the record.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def calPoints(self, ops: List[str]) -> int:
        stk = []
        for op in ops:
            if op == '+':
                stk.append(stk[-1] + stk[-2])
            elif op == 'D':
                stk.append(stk[-1] << 1)
            elif op == 'C':
                stk.pop()
            else:
                stk.append(int(op))
        return sum(stk)
```

### **Java**

```java
class Solution {
    public int calPoints(String[] ops) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (String op : ops) {
            if ("+".equals(op)) {
                int a = stk.pop();
                int b = stk.peek();
                stk.push(a);
                stk.push(a + b);
            } else if ("D".equals(op)) {
                stk.push(stk.peek() << 1);
            } else if ("C".equals(op)) {
                stk.pop();
            } else {
                stk.push(Integer.valueOf(op));
            }
        }
        return stk.stream().mapToInt(Integer::intValue).sum();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int calPoints(vector<string>& ops) {
        vector<int> stk;
        for (auto& op : ops) {
            int n = stk.size();
            if (op == "+") {
                int a = stk[n - 1];
                int b = stk[n - 2];
                stk.push_back(a + b);
            } else if (op == "D")
                stk.push_back(stk[n - 1] * 2);
            else if (op == "C")
                stk.pop_back();
            else
                stk.push_back(stoi(op));
        }
        return accumulate(stk.begin(), stk.end(), 0);
    }
};
```

### **Go**

```go
func calPoints(ops []string) int {
	var stk []int
	for _, op := range ops {
		n := len(stk)
		switch op {
		case "+":
			stk = append(stk, stk[n-1]+stk[n-2])
		case "D":
			stk = append(stk, stk[n-1]*2)
		case "C":
			stk = stk[:n-1]
		default:
			num, _ := strconv.Atoi(op)
			stk = append(stk, num)
		}
	}
	ans := 0
	for _, score := range stk {
		ans += score
	}
	return ans
}
```

### **TypeScript**

```ts
function calPoints(ops: string[]): number {
    const stack = [];
    for (const op of ops) {
        const n = stack.length;
        if (op === '+') {
            stack.push(stack[n - 1] + stack[n - 2]);
        } else if (op === 'D') {
            stack.push(stack[n - 1] * 2);
        } else if (op === 'C') {
            stack.pop();
        } else {
            stack.push(Number(op));
        }
    }
    return stack.reduce((p, v) => p + v);
}
```

### **Rust**

```rust
impl Solution {
    pub fn cal_points(ops: Vec<String>) -> i32 {
        let mut stack = vec![];
        for op in ops {
            match op.as_str() {
                "+" => {
                    let n = stack.len();
                    stack.push(stack[n - 1] + stack[n - 2]);
                }
                "D" => {
                    stack.push(stack.last().unwrap() * 2);
                }
                "C" => {
                    stack.pop();
                }
                n => {
                    stack.push(n.parse::<i32>().unwrap());
                }
            }
        }
        stack.into_iter().sum()
    }
}
```

### **...**

```

```

<!-- tabs:end -->

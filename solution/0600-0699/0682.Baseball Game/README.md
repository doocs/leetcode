# [682. 棒球比赛](https://leetcode.cn/problems/baseball-game)

[English Version](/solution/0600-0699/0682.Baseball%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。</p>

<p>比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 <code>ops</code>，其中 <code>ops[i]</code> 是你需要记录的第 <code>i</code> 项操作，<code>ops</code> 遵循下述规则：</p>

<ol>
	<li>整数 <code>x</code> - 表示本回合新获得分数 <code>x</code></li>
	<li><code>"+"</code> - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。</li>
	<li><code>"D"</code> - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。</li>
	<li><code>"C"</code> - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。</li>
</ol>

<p>请你返回记录中所有得分的总和。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ops = ["5","2","C","D","+"]
<strong>输出：</strong>30
<strong>解释：</strong>
"5" - 记录加 5 ，记录现在是 [5]
"2" - 记录加 2 ，记录现在是 [5, 2]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
"D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
"+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
所有得分的总和 5 + 10 + 15 = 30
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>ops = ["5","-2","4","C","D","9","+","+"]
<strong>输出：</strong>27
<strong>解释：</strong>
"5" - 记录加 5 ，记录现在是 [5]
"-2" - 记录加 -2 ，记录现在是 [5, -2]
"4" - 记录加 4 ，记录现在是 [5, -2, 4]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
"D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
"9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
"+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
"+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>ops = ["1"]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= ops.length <= 1000</code></li>
	<li><code>ops[i]</code> 为 <code>"C"</code>、<code>"D"</code>、<code>"+"</code>，或者一个表示整数的字符串。整数范围是 <code>[-3 * 10<sup>4</sup>, 3 * 10<sup>4</sup>]</code></li>
	<li>对于 <code>"+"</code> 操作，题目数据保证记录此操作时前面总是存在两个有效的分数</li>
	<li>对于 <code>"C"</code> 和 <code>"D"</code> 操作，题目数据保证记录此操作时前面总是存在一个有效的分数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

利用栈简单模拟即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

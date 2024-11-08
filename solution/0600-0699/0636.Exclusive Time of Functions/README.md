---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/README.md
tags:
    - 栈
    - 数组
---

<!-- problem:start -->

# [636. 函数的独占时间](https://leetcode.cn/problems/exclusive-time-of-functions)

[English Version](/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有一个 <strong>单线程</strong> CPU 正在运行一个含有 <code>n</code> 道函数的程序。每道函数都有一个位于&nbsp; <code>0</code> 和 <code>n-1</code> 之间的唯一标识符。</p>

<p>函数调用 <strong>存储在一个 <a href="https://baike.baidu.com/item/%E8%B0%83%E7%94%A8%E6%A0%88/22718047?fr=aladdin" target="_blank">调用栈</a> 上</strong> ：当一个函数调用开始时，它的标识符将会推入栈中。而当一个函数调用结束时，它的标识符将会从栈中弹出。标识符位于栈顶的函数是 <strong>当前正在执行的函数</strong> 。每当一个函数开始或者结束时，将会记录一条日志，包括函数标识符、是开始还是结束、以及相应的时间戳。</p>

<p>给你一个由日志组成的列表 <code>logs</code> ，其中 <code>logs[i]</code> 表示第 <code>i</code> 条日志消息，该消息是一个按 <code>"{function_id}:{"start" | "end"}:{timestamp}"</code> 进行格式化的字符串。例如，<code>"0:start:3"</code> 意味着标识符为 <code>0</code> 的函数调用在时间戳 <code>3</code> 的 <strong>起始开始执行</strong> ；而 <code>"1:end:2"</code> 意味着标识符为 <code>1</code> 的函数调用在时间戳 <code>2</code> 的 <strong>末尾结束执行</strong>。注意，函数可以 <strong>调用多次，可能存在递归调用 </strong>。</p>

<p>函数的 <strong>独占时间</strong> 定义是在这个函数在程序所有函数调用中执行时间的总和，调用其他函数花费的时间不算该函数的独占时间。例如，如果一个函数被调用两次，一次调用执行 <code>2</code> 单位时间，另一次调用执行 <code>1</code> 单位时间，那么该函数的 <strong>独占时间</strong> 为 <code>2 + 1 = 3</code> 。</p>

<p>以数组形式返回每个函数的 <strong>独占时间</strong> ，其中第 <code>i</code> 个下标对应的值表示标识符 <code>i</code> 的函数的独占时间。</p>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0636.Exclusive%20Time%20of%20Functions/images/diag1b.png" style="width: 550px; height: 239px;" />
<pre>
<strong>输入：</strong>n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
<strong>输出：</strong>[3,4]
<strong>解释：</strong>
函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，于时间戳 1 的末尾结束执行。 
函数 1 在时间戳 2 的起始开始执行，执行 4 个单位时间，于时间戳 5 的末尾结束执行。 
函数 0 在时间戳 6 的开始恢复执行，执行 1 个单位时间。 
所以函数 0 总共执行 2 + 1 = 3 个单位时间，函数 1 总共执行 4 个单位时间。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
<strong>输出：</strong>[8]
<strong>解释：</strong>
函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，并递归调用它自身。
函数 0（递归调用）在时间戳 2 的起始开始执行，执行 4 个单位时间。
函数 0（初始调用）恢复执行，并立刻再次调用它自身。
函数 0（第二次递归调用）在时间戳 6 的起始开始执行，执行 1 个单位时间。
函数 0（初始调用）在时间戳 7 的起始恢复执行，执行 1 个单位时间。
所以函数 0 总共执行 2 + 4 + 1 + 1 = 8 个单位时间。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
<strong>输出：</strong>[7,1]
<strong>解释：</strong>
函数 0 在时间戳 0 的起始开始执行，执行 2 个单位时间，并递归调用它自身。
函数 0（递归调用）在时间戳 2 的起始开始执行，执行 4 个单位时间。
函数 0（初始调用）恢复执行，并立刻调用函数 1 。
函数 1在时间戳 6 的起始开始执行，执行 1 个单位时间，于时间戳 6 的末尾结束执行。
函数 0（初始调用）在时间戳 7 的起始恢复执行，执行 1 个单位时间，于时间戳 7 的末尾结束执行。
所以函数 0 总共执行 2 + 4 + 1 = 7 个单位时间，函数 1 总共执行 1 个单位时间。 </pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= logs.length &lt;= 500</code></li>
	<li><code>0 &lt;= function_id &lt; n</code></li>
	<li><code>0 &lt;= timestamp &lt;= 10<sup>9</sup></code></li>
	<li>两个开始事件不会在同一时间戳发生</li>
	<li>两个结束事件不会在同一时间戳发生</li>
	<li>每道函数都有一个对应&nbsp;<code>"start"</code> 日志的 <code>"end"</code> 日志</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈 + 模拟

我们定义一个栈 $\textit{stk}$，用于存储当前正在执行的函数的标识符。同时，我们定义一个数组 $\textit{ans}$，用于存储每个函数的独占时间，初始时每个函数的独占时间都为 $0$。用一个变量 $\textit{pre}$ 记录上一个时间戳。

遍历日志数组，对于每一条日志，我们首先将其按照冒号分隔，得到函数标识符 $\textit{i}$，操作类型 $\textit{op}$ 和时间戳 $\textit{t}$。

如果 $\textit{op}$ 为 $\text{start}$，则表示函数 $\textit{i}$ 开始执行，我们需要判断栈是否为空，如果不为空，则将栈顶函数的独占时间增加 $\textit{cur} - \textit{pre}$，然后将 $\textit{i}$ 入栈，更新 $\textit{pre}$ 为 $\textit{cur}$；如果 $\textit{op}$ 为 $\text{end}$，则表示函数 $\textit{i}$ 结束执行，我们将栈顶函数的独占时间增加 $\textit{cur} - \textit{pre} + 1$，然后将栈顶元素出栈，更新 $\textit{pre}$ 为 $\textit{cur} + 1$。

最后返回数组 $\textit{ans}$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为日志数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        stk = []
        ans = [0] * n
        pre = 0
        for log in logs:
            i, op, t = log.split(":")
            i, cur = int(i), int(t)
            if op[0] == "s":
                if stk:
                    ans[stk[-1]] += cur - pre
                stk.append(i)
                pre = cur
            else:
                ans[stk.pop()] += cur - pre + 1
                pre = cur + 1
        return ans
```

#### Java

```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> stk = new ArrayDeque<>();
        int pre = 0;
        for (var log : logs) {
            var parts = log.split(":");
            int i = Integer.parseInt(parts[0]);
            int cur = Integer.parseInt(parts[2]);
            if (parts[1].charAt(0) == 's') {
                if (!stk.isEmpty()) {
                    ans[stk.peek()] += cur - pre;
                }
                stk.push(i);
                pre = cur;
            } else {
                ans[stk.pop()] += cur - pre + 1;
                pre = cur + 1;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> exclusiveTime(int n, vector<string>& logs) {
        vector<int> ans(n);
        stack<int> stk;
        int pre = 0;
        for (const auto& log : logs) {
            int i, cur;
            char c[10];
            sscanf(log.c_str(), "%d:%[^:]:%d", &i, c, &cur);
            if (c[0] == 's') {
                if (stk.size()) {
                    ans[stk.top()] += cur - pre;
                }
                stk.push(i);
                pre = cur;
            } else {
                ans[stk.top()] += cur - pre + 1;
                stk.pop();
                pre = cur + 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func exclusiveTime(n int, logs []string) []int {
	ans := make([]int, n)
	stk := []int{}
	pre := 0
	for _, log := range logs {
		parts := strings.Split(log, ":")
		i, _ := strconv.Atoi(parts[0])
		cur, _ := strconv.Atoi(parts[2])
		if parts[1][0] == 's' {
			if len(stk) > 0 {
				ans[stk[len(stk)-1]] += cur - pre
			}
			stk = append(stk, i)
			pre = cur
		} else {
			ans[stk[len(stk)-1]] += cur - pre + 1
			stk = stk[:len(stk)-1]
			pre = cur + 1
		}
	}
	return ans
}
```

#### TypeScript

```ts
function exclusiveTime(n: number, logs: string[]): number[] {
    const ans: number[] = Array(n).fill(0);
    let pre = 0;
    const stk: number[] = [];
    for (const log of logs) {
        const [i, op, cur] = log.split(':');
        if (op[0] === 's') {
            if (stk.length) {
                ans[stk.at(-1)!] += +cur - pre;
            }
            stk.push(+i);
            pre = +cur;
        } else {
            ans[stk.pop()!] += +cur - pre + 1;
            pre = +cur + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

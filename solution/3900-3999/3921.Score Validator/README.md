---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3921.Score%20Validator/README.md
rating: 1262
source: 第 182 场双周赛 Q1
---

<!-- problem:start -->

# [3921. 分数验证器](https://leetcode.cn/problems/score-validator)

[English Version](/solution/3900-3999/3921.Score%20Validator/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>events</code>。</p>

<p>一开始，<code>score = 0</code> 且 <code>counter = 0</code>。<code>events</code> 中的每个元素为以下之一：</p>

<ul>
	<li><code>"0"</code>, <code>"1"</code>, <code>"2"</code>, <code>"3"</code>, <code>"4"</code>, <code>"6"</code>：将该值加到总得分中。</li>
	<li><code>"W"</code>：计数器加 1。不增加得分。</li>
	<li><code>"WD"</code>：总得分加 1。</li>
	<li><code>"NB"</code>：总得分加 1。</li>
</ul>

<p>从左到右处理数组。当满足以下任一条件时停止处理：</p>

<ul>
	<li><code>events</code> 中的所有元素都已处理完毕，或</li>
	<li>计数器变为 10。</li>
</ul>

<p>返回一个整数数组 <code>[score, counter]</code>，其中：</p>

<ul>
	<li><code>score</code> 是最终的总得分。</li>
	<li><code>counter</code> 是最终的计数器值。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">events = ["1","4","W","6","WD"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[12,1]</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>事件</th>
			<th>得分</th>
			<th>计数器</th>
		</tr>
		<tr>
			<td><code>"1"</code></td>
			<td>1</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>"4"</code></td>
			<td>5</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>"W"</code></td>
			<td>5</td>
			<td>1</td>
		</tr>
		<tr>
			<td><code>"6"</code></td>
			<td>11</td>
			<td>1</td>
		</tr>
		<tr>
			<td><code>"WD"</code></td>
			<td>12</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>最终结果：<code>[12, 1]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">events = ["WD","NB","0","4","4"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[10,0]</span></p>

<p><strong>解释：</strong></p>

<table>
	<tbody>
		<tr>
			<th>事件</th>
			<th>得分</th>
			<th>计数器</th>
		</tr>
		<tr>
			<td><code>"WD"</code></td>
			<td>1</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>"NB"</code></td>
			<td>2</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>"0"</code></td>
			<td>2</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>"4"</code></td>
			<td>6</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>"4"</code></td>
			<td>10</td>
			<td>0</td>
		</tr>
	</tbody>
</table>

<p>最终结果：<code>[10, 0]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">events = ["W","W","W","W","W","W","W","W","W","W","W"]</span></p>

<p><strong>输出：</strong> <span class="example-io">[0,10]</span></p>

<p><strong>解释：</strong></p>

<p>出现 10 次 <code>"W"</code> 后，计数器达到 10，因此停止处理。剩余的事件将被忽略。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 1000</code></li>
	<li><code>events[i]</code> 是 <code>"0"</code>、<code>"1"</code>、<code>"2"</code>、<code>"3"</code>、<code>"4"</code>、<code>"6"</code>、<code>"W"</code>、<code>"WD"</code> 或 <code>"NB"</code> 之一。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟题目中描述的过程来计算最终的得分和计数器值。

首先，我们初始化两个变量 $\textit{score}$ 和 $\textit{counter}$，分别表示当前的总得分和计数器值。然后我们遍历数组 $\textit{events}$ 中的每个事件，根据事件的类型来更新 $\textit{score}$ 和 $\textit{counter}$ 的值：

- 如果事件是数字字符串，我们将该数字转换为整数并加到 $\textit{score}$ 中。
- 如果事件是字符串 "W"，我们将 $\textit{counter}$ 加 1，并检查是否达到了 10，如果达到了就停止处理。
- 否则（事件是 "WD" 或 "NB"），我们将 $\textit{score}$ 加 1。

在处理完所有事件或者计数器达到 10 后，我们返回一个数组，包含最终的 $\textit{score}$ 和 $\textit{counter}$ 的值。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{events}$ 的长度。空间复杂度 $O(1)$，我们只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def scoreValidator(self, events: list[str]) -> list[int]:
        score = counter = 0
        for event in events:
            if event.isdigit():
                score += int(event)
            elif event == "W":
                counter += 1
                if counter == 10:
                    break
            else:
                score += 1
        return [score, counter]
```

#### Java

```java
class Solution {
    public int[] scoreValidator(String[] events) {
        int score = 0;
        int counter = 0;
        for (String event : events) {
            if (event.matches("\\d+")) {
                score += Integer.parseInt(event);
            } else if (event.equals("W")) {
                if (++counter == 10) {
                    break;
                }
            } else {
                score++;
            }
        }
        return new int[] {score, counter};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> scoreValidator(vector<string>& events) {
        int score = 0;
        int counter = 0;
        for (string event : events) {
            if (isdigit(event[0])) {
                score += stoi(event);
            } else if (event == "W") {
                if (++counter == 10) {
                    break;
                }
            } else {
                score++;
            }
        }
        return {score, counter};
    }
};
```

#### Go

```go
func scoreValidator(events []string) []int {
	score := 0
	counter := 0
	for _, event := range events {
		if num, err := strconv.Atoi(event); err == nil {
			score += num
		} else if event == "W" {
			counter++
			if counter == 10 {
				break
			}
		} else {
			score++
		}
	}
	return []int{score, counter}
}
```

#### TypeScript

```ts
function scoreValidator(events: string[]): number[] {
    let score = 0;
    let counter = 0;
    for (const event of events) {
        if (/^\d+$/.test(event)) {
            score += parseInt(event);
        } else if (event === 'W') {
            counter++;
            if (counter === 10) {
                break;
            }
        } else {
            score++;
        }
    }
    return [score, counter];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

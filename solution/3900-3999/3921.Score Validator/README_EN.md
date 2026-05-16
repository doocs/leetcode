---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3921.Score%20Validator/README_EN.md
rating: 1262
source: Biweekly Contest 182 Q1
---

<!-- problem:start -->

# [3921. Score Validator](https://leetcode.com/problems/score-validator)

[中文文档](/solution/3900-3999/3921.Score%20Validator/README.md)

## Description

<!-- description:start -->

<p>You are given a string array <code>events</code>.</p>

<p>Initially, <code>score = 0</code> and <code>counter = 0</code>. Each element in <code>events</code> is one of the following:</p>

<ul>
	<li><code>&quot;0&quot;</code>, <code>&quot;1&quot;</code>, <code>&quot;2&quot;</code>, <code>&quot;3&quot;</code>, <code>&quot;4&quot;</code>, <code>&quot;6&quot;</code>: Add that value to the total score.</li>
	<li><code>&quot;W&quot;</code>: Increase the counter by 1. No score is added.</li>
	<li><code>&quot;WD&quot;</code>: Add 1 to the total score.</li>
	<li><code>&quot;NB&quot;</code>: Add 1 to the total score.</li>
</ul>

<p>Process the array from left to right. Stop processing when either:</p>

<ul>
	<li>All elements in <code>events</code> have been processed, or</li>
	<li>The counter becomes 10.</li>
</ul>

<p>Return an integer array <code>[score, counter]</code>, where:</p>

<ul>
	<li><code>score</code> is the final total score.</li>
	<li><code>counter</code> is the final counter value.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">events = [&quot;1&quot;,&quot;4&quot;,&quot;W&quot;,&quot;6&quot;,&quot;WD&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[12,1]</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Event</th>
			<th>Score</th>
			<th>Counter</th>
		</tr>
		<tr>
			<td><code>&quot;1&quot;</code></td>
			<td>1</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>&quot;4&quot;</code></td>
			<td>5</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>&quot;W&quot;</code></td>
			<td>5</td>
			<td>1</td>
		</tr>
		<tr>
			<td><code>&quot;6&quot;</code></td>
			<td>11</td>
			<td>1</td>
		</tr>
		<tr>
			<td><code>&quot;WD&quot;</code></td>
			<td>12</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p>Final result: <code>[12, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">events = [&quot;WD&quot;,&quot;NB&quot;,&quot;0&quot;,&quot;4&quot;,&quot;4&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[10,0]</span></p>

<p><strong>Explanation:</strong></p>

<table>
	<tbody>
		<tr>
			<th>Event</th>
			<th>Score</th>
			<th>Counter</th>
		</tr>
		<tr>
			<td><code>&quot;WD&quot;</code></td>
			<td>1</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>&quot;NB&quot;</code></td>
			<td>2</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>&quot;0&quot;</code></td>
			<td>2</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>&quot;4&quot;</code></td>
			<td>6</td>
			<td>0</td>
		</tr>
		<tr>
			<td><code>&quot;4&quot;</code></td>
			<td>10</td>
			<td>0</td>
		</tr>
	</tbody>
</table>

<p>Final result: <code>[10, 0]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">events = [&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;,&quot;W&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,10]</span></p>

<p><strong>Explanation:</strong></p>

<p>After 10 occurrences of <code>&quot;W&quot;</code>, the counter reaches 10, so processing stops. The remaining events are ignored.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 1000</code></li>
	<li><code>events[i]</code> is one of <code>&quot;0&quot;</code>, <code>&quot;1&quot;</code>, <code>&quot;2&quot;</code>, <code>&quot;3&quot;</code>, <code>&quot;4&quot;</code>, <code>&quot;6&quot;</code>, <code>&quot;W&quot;</code>, <code>&quot;WD&quot;</code>, or <code>&quot;NB&quot;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate the process described in the problem to calculate the final score and counter value.

First, we initialize two variables $\textit{score}$ and $\textit{counter}$, representing the current total score and counter value respectively. Then we iterate through each event in the array $\textit{events}$ and update $\textit{score}$ and $\textit{counter}$ based on the event type:

- If the event is a numeric string, we convert it to an integer and add it to $\textit{score}$.
- If the event is the string `"W"`, we increment $\textit{counter}$ by 1 and check if it has reached 10; if so, we stop processing.
- Otherwise (the event is `"WD"` or `"NB"`), we add 1 to $\textit{score}$.

After processing all events or when the counter reaches 10, we return an array containing the final values of $\textit{score}$ and $\textit{counter}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{events}$. The space complexity is $O(1)$, as we only use a constant amount of extra space.

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

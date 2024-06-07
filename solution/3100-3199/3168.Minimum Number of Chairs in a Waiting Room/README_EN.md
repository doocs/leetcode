---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3168.Minimum%20Number%20of%20Chairs%20in%20a%20Waiting%20Room/README_EN.md
tags:
    - String
    - Simulation
---

<!-- problem:start -->

# [3168. Minimum Number of Chairs in a Waiting Room](https://leetcode.com/problems/minimum-number-of-chairs-in-a-waiting-room)

[中文文档](/solution/3100-3199/3168.Minimum%20Number%20of%20Chairs%20in%20a%20Waiting%20Room/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code>. Simulate events at each second <code>i</code>:</p>

<ul>
	<li>If <code>s[i] == &#39;E&#39;</code>, a person enters the waiting room and takes one of the chairs in it.</li>
	<li>If <code>s[i] == &#39;L&#39;</code>, a person leaves the waiting room, freeing up a chair.</li>
</ul>

<p>Return the <strong>minimum </strong>number of chairs needed so that a chair is available for every person who enters the waiting room given that it is initially <strong>empty</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;EEEEEEE&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>After each second, a person enters the waiting room and no person leaves it. Therefore, a minimum of 7 chairs is needed.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ELELEEL&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s consider that there are 2 chairs in the waiting room. The table below shows the state of the waiting room at each second.</p>
</div>

<table>
	<tbody>
		<tr>
			<th>Second</th>
			<th>Event</th>
			<th>People in the Waiting Room</th>
			<th>Available Chairs</th>
		</tr>
		<tr>
			<td>0</td>
			<td>Enter</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>1</td>
			<td>Leave</td>
			<td>0</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Enter</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Leave</td>
			<td>0</td>
			<td>2</td>
		</tr>
		<tr>
			<td>4</td>
			<td>Enter</td>
			<td>1</td>
			<td>1</td>
		</tr>
		<tr>
			<td>5</td>
			<td>Enter</td>
			<td>2</td>
			<td>0</td>
		</tr>
		<tr>
			<td>6</td>
			<td>Leave</td>
			<td>1</td>
			<td>1</td>
		</tr>
	</tbody>
</table>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;ELEELEELLL&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Let&#39;s consider that there are 3 chairs in the waiting room. The table below shows the state of the waiting room at each second.</p>
</div>

<table>
	<tbody>
		<tr>
			<th>Second</th>
			<th>Event</th>
			<th>People in the Waiting Room</th>
			<th>Available Chairs</th>
		</tr>
		<tr>
			<td>0</td>
			<td>Enter</td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>1</td>
			<td>Leave</td>
			<td>0</td>
			<td>3</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Enter</td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Enter</td>
			<td>2</td>
			<td>1</td>
		</tr>
		<tr>
			<td>4</td>
			<td>Leave</td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>5</td>
			<td>Enter</td>
			<td>2</td>
			<td>1</td>
		</tr>
		<tr>
			<td>6</td>
			<td>Enter</td>
			<td>3</td>
			<td>0</td>
		</tr>
		<tr>
			<td>7</td>
			<td>Leave</td>
			<td>2</td>
			<td>1</td>
		</tr>
		<tr>
			<td>8</td>
			<td>Leave</td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>9</td>
			<td>Leave</td>
			<td>0</td>
			<td>3</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>s</code> consists only of the letters <code>&#39;E&#39;</code> and <code>&#39;L&#39;</code>.</li>
	<li><code>s</code> represents a valid sequence of entries and exits.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable `cnt` to record the current number of chairs needed, and a variable `left` to record the current number of remaining empty chairs. We traverse the string `s`. If the current character is 'E', then if there are remaining empty chairs, we directly use one empty chair, otherwise we need to add a chair; if the current character is 'L', then the number of remaining empty chairs increases by one.

After the traversal, we return `cnt`.

The time complexity is $O(n)$, where $n$ is the length of the string `s`. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumChairs(self, s: str) -> int:
        cnt = left = 0
        for c in s:
            if c == "E":
                if left:
                    left -= 1
                else:
                    cnt += 1
            else:
                left += 1
        return cnt
```

#### Java

```java
class Solution {
    public int minimumChairs(String s) {
        int cnt = 0, left = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'E') {
                if (left > 0) {
                    --left;
                } else {
                    ++cnt;
                }
            } else {
                ++left;
            }
        }
        return cnt;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumChairs(string s) {
        int cnt = 0, left = 0;
        for (char& c : s) {
            if (c == 'E') {
                if (left > 0) {
                    --left;
                } else {
                    ++cnt;
                }
            } else {
                ++left;
            }
        }
        return cnt;
    }
};
```

#### Go

```go
func minimumChairs(s string) int {
	cnt, left := 0, 0
	for _, c := range s {
		if c == 'E' {
			if left > 0 {
				left--
			} else {
				cnt++
			}
		} else {
			left++
		}
	}
	return cnt
}
```

#### TypeScript

```ts
function minimumChairs(s: string): number {
    let [cnt, left] = [0, 0];
    for (const c of s) {
        if (c === 'E') {
            if (left > 0) {
                --left;
            } else {
                ++cnt;
            }
        } else {
            ++left;
        }
    }
    return cnt;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

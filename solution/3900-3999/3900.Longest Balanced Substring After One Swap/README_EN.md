---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3900.Longest%20Balanced%20Substring%20After%20One%20Swap/README_EN.md
rating: 2134
source: Weekly Contest 497 Q3
---

<!-- problem:start -->

# [3900. Longest Balanced Substring After One Swap](https://leetcode.com/problems/longest-balanced-substring-after-one-swap)

[中文文档](/solution/3900-3999/3900.Longest%20Balanced%20Substring%20After%20One%20Swap/README.md)

## Description

<!-- description:start -->

<p>You are given a binary string <code>s</code> consisting only of characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</p>

<p>A string is <strong>balanced</strong> if it contains an <strong>equal</strong> number of <code>&#39;0&#39;</code>s and <code>&#39;1&#39;</code>s.</p>

<p>You can perform <strong>at most one</strong> swap between any two characters in <code>s</code>. Then, you select a <strong>balanced</strong> <span data-keyword="substring">substring</span> from <code>s</code>.</p>

<p>Return an integer representing the <strong>maximum</strong> length of the <strong>balanced</strong> substring you can select.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;100001&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Swap <code>&quot;10<u><strong>0</strong></u>00<u><strong>1</strong></u>&quot;</code>. The string becomes <code>&quot;101000&quot;</code>.</li>
	<li>Select the substring <code>&quot;<u><strong>1010</strong></u>00&quot;</code>, which is balanced because it has two <code>&#39;0&#39;</code>s and two <code>&#39;1&#39;</code>s.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;111&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose not to perform any swaps.</li>
	<li>Select the empty substring, which is balanced because it has zero <code>&#39;0&#39;</code>s and zero <code>&#39;1&#39;</code>s.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of the characters <code>&#39;0&#39;</code> and <code>&#39;1&#39;</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Hash Table

Let the prefix sum $\textit{pre}$ denote the number of `1`s minus the number of `0`s in the current prefix. Then for any substring, if the numbers of `0`s and `1`s are equal, its corresponding prefix sum difference is $0$.

Therefore, if the prefix sum at position $i$ is $x$, and some previous position also has prefix sum $x$, then the substring between these two positions is balanced, and we can directly use it to update the answer.

Now the problem allows us to perform at most one swap between any two characters. One swap can only reduce the difference between the counts of `1` and `0` in a substring by $2$. So besides the case where the prefix sum difference is $0$, we also need to consider:

- A prefix sum difference of $2$, which means the substring contains 2 more `1`s than `0`s. In this case, if there is still at least one `0` outside the substring, we can make it balanced with one swap.
- A prefix sum difference of $-2$, which means the substring contains 2 more `0`s than `1`s. Similarly, if there is still at least one `1` outside the substring, we can make it balanced with one swap.

To do this, we first count the total numbers of `0`s and `1`s in the whole string, denoted by $\textit{cnt0}$ and $\textit{cnt1}$. Then we use a hash table to record all positions where each prefix sum appears.

While traversing the string up to position $i$, let the current prefix sum be $\textit{pre}$:

- Use the earliest occurrence of $\textit{pre}$ to update the longest balanced substring length without any swap.
- If prefix sum $\textit{pre} - 2$ exists, then we can try to form a substring with 2 more `1`s than `0`s. Suppose its length is $L$. Then the number of `0`s inside it is $(L - 2) / 2$. Only when this value is strictly less than $\textit{cnt0}$ do we know there is at least one `0` outside the substring that can be swapped in.
- If prefix sum $\textit{pre} + 2$ exists, we can similarly try to form a substring with 2 more `0`s than `1`s. In this case, we need the number of `1`s inside it to be strictly less than $\textit{cnt1}$.

Since an earlier occurrence of the same prefix sum gives a longer substring, we always try the earliest position first. If it cannot satisfy the condition that there is still a character outside the substring available for swapping, we try the second earliest position.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestBalanced(self, s: str) -> int:
        cnt0 = s.count("0")
        cnt1 = len(s) - cnt0
        pos = {0: [-1]}
        ans = pre = 0
        for i, c in enumerate(s):
            pre += 1 if c == "1" else -1
            pos.setdefault(pre, []).append(i)

            ans = max(ans, i - pos[pre][0])
            if pre - 2 in pos:
                p = pos[pre - 2]
                if (i - p[0] - 2) // 2 < cnt0:
                    ans = max(ans, i - p[0])
                elif len(p) > 1:
                    ans = max(ans, i - p[1])

            if pre + 2 in pos:
                p = pos[pre + 2]
                if (i - p[0] - 2) // 2 < cnt1:
                    ans = max(ans, i - p[0])
                elif len(p) > 1:
                    ans = max(ans, i - p[1])
        return ans
```

#### Java

```java
class Solution {
    public int longestBalanced(String s) {
        int cnt0 = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                ++cnt0;
            }
        }
        int cnt1 = s.length() - cnt0;
        Map<Integer, List<Integer>> pos = new HashMap<>();
        pos.put(0, new ArrayList<>(List.of(-1)));
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < s.length(); ++i) {
            pre += s.charAt(i) == '1' ? 1 : -1;
            pos.computeIfAbsent(pre, k -> new ArrayList<>()).add(i);

            ans = Math.max(ans, i - pos.get(pre).get(0));
            if (pos.containsKey(pre - 2)) {
                List<Integer> p = pos.get(pre - 2);
                if ((i - p.get(0) - 2) / 2 < cnt0) {
                    ans = Math.max(ans, i - p.get(0));
                } else if (p.size() > 1) {
                    ans = Math.max(ans, i - p.get(1));
                }
            }

            if (pos.containsKey(pre + 2)) {
                List<Integer> p = pos.get(pre + 2);
                if ((i - p.get(0) - 2) / 2 < cnt1) {
                    ans = Math.max(ans, i - p.get(0));
                } else if (p.size() > 1) {
                    ans = Math.max(ans, i - p.get(1));
                }
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
    int longestBalanced(string s) {
        int cnt0 = count(s.begin(), s.end(), '0');
        int cnt1 = s.size() - cnt0;
        unordered_map<int, vector<int>> pos;
        pos[0] = {-1};
        int ans = 0, pre = 0;
        for (int i = 0; i < s.size(); ++i) {
            pre += s[i] == '1' ? 1 : -1;
            pos[pre].push_back(i);

            ans = max(ans, i - pos[pre][0]);
            if (pos.contains(pre - 2)) {
                auto& p = pos[pre - 2];
                if ((i - p[0] - 2) / 2 < cnt0) {
                    ans = max(ans, i - p[0]);
                } else if (p.size() > 1) {
                    ans = max(ans, i - p[1]);
                }
            }

            if (pos.contains(pre + 2)) {
                auto& p = pos[pre + 2];
                if ((i - p[0] - 2) / 2 < cnt1) {
                    ans = max(ans, i - p[0]);
                } else if (p.size() > 1) {
                    ans = max(ans, i - p[1]);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestBalanced(s string) int {
	cnt0 := strings.Count(s, "0")
	cnt1 := len(s) - cnt0
	pos := map[int][]int{0: {-1}}
	ans, pre := 0, 0
	for i, c := range s {
		if c == '1' {
			pre++
		} else {
			pre--
		}
		pos[pre] = append(pos[pre], i)

		ans = max(ans, i-pos[pre][0])
		if p, ok := pos[pre-2]; ok {
			if (i-p[0]-2)/2 < cnt0 {
				ans = max(ans, i-p[0])
			} else if len(p) > 1 {
				ans = max(ans, i-p[1])
			}
		}

		if p, ok := pos[pre+2]; ok {
			if (i-p[0]-2)/2 < cnt1 {
				ans = max(ans, i-p[0])
			} else if len(p) > 1 {
				ans = max(ans, i-p[1])
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestBalanced(s: string): number {
    const cnt0 = [...s].filter(c => c === '0').length;
    const cnt1 = s.length - cnt0;
    const pos = new Map<number, number[]>();
    pos.set(0, [-1]);
    let ans = 0;
    let pre = 0;
    for (let i = 0; i < s.length; ++i) {
        pre += s[i] === '1' ? 1 : -1;
        if (!pos.has(pre)) {
            pos.set(pre, []);
        }
        pos.get(pre)!.push(i);

        ans = Math.max(ans, i - pos.get(pre)![0]);
        if (pos.has(pre - 2)) {
            const p = pos.get(pre - 2)!;
            if ((i - p[0] - 2) >> 1 < cnt0) {
                ans = Math.max(ans, i - p[0]);
            } else if (p.length > 1) {
                ans = Math.max(ans, i - p[1]);
            }
        }

        if (pos.has(pre + 2)) {
            const p = pos.get(pre + 2)!;
            if ((i - p[0] - 2) >> 1 < cnt1) {
                ans = Math.max(ans, i - p[0]);
            } else if (p.length > 1) {
                ans = Math.max(ans, i - p[1]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

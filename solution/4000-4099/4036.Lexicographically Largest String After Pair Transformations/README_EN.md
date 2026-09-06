---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4036.Lexicographically%20Largest%20String%20After%20Pair%20Transformations/README_EN.md
rating: 1704
source: Biweekly Contest 190 Q3
---

<!-- problem:start -->

# [4036. Lexicographically Largest String After Pair Transformations](https://leetcode.com/problems/lexicographically-largest-string-after-pair-transformations)

[中文文档](/solution/4000-4099/4036.Lexicographically%20Largest%20String%20After%20Pair%20Transformations/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>For each integer <code>x</code> in <code>nums</code>, start with a string consisting of exactly <code>x</code> lowercase <code>&#39;a&#39;</code> characters.</p>

<p>You may perform the following operation any number of times (including zero):</p>

<ul>
	<li>Choose two <strong>adjacent equal</strong> letters and replace them with the next letter in the alphabet.</li>
</ul>

<p>For example, <code>&quot;aa&quot;</code> can be replaced with <code>&quot;b&quot;</code>, and <code>&quot;bb&quot;</code> can be replaced with <code>&quot;c&quot;</code>. The pair <code>&quot;zz&quot;</code> cannot be replaced.</p>

<p>For each <code>x</code>, determine the <strong>lexicographically largest</strong> string that can be obtained.</p>

<p>Return an array of strings where the <code>i<sup>th</sup></code> string is the answer for <code>nums[i]</code>.</p>

<p>A string <code>a</code> is <strong>lexicographically larger</strong> than a string <code>b</code> if, at the first position where they differ, <code>a</code> contains a letter that appears later in the alphabet than the corresponding letter in <code>b</code>. If the first <code>min(a.length, b.length)</code> characters are equal, the longer string is lexicographically larger.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;b&quot;,&quot;ca&quot;,&quot;cba&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0] = 2</code>: <code>&quot;aa&quot;</code> &rarr; <code>&quot;b&quot;</code>.</li>
	<li><code>nums[1] = 5</code>: <code>&quot;aaaaa&quot;</code> &rarr; <code>&quot;baaa&quot;</code> &rarr; <code>&quot;bba&quot;</code> &rarr; <code>&quot;ca&quot;</code>.</li>
	<li><code>nums[2] = 7</code>: <code>&quot;aaaaaaa&quot;</code> &rarr; <code>&quot;baaaaa&quot;</code> &rarr; <code>&quot;bbaaa&quot;</code> &rarr; <code>&quot;bbba&quot;</code> &rarr; <code>&quot;cba&quot;</code>.</li>
	<li>Therefore, <code>ans = [&quot;b&quot;, &quot;ca&quot;, &quot;cba&quot;]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,9,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[&quot;ba&quot;,&quot;da&quot;,&quot;a&quot;]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>nums[0] = 3</code>: <code>&quot;aaa&quot;</code> &rarr; <code>&quot;ba&quot;</code>.</li>
	<li><code>nums[1] = 9</code>: <code>&quot;aaaaaaaaa&quot;</code> &rarr; <code>&quot;baaaaaaa&quot;</code> &rarr; <code>&quot;bbaaaaa&quot;</code> &rarr; <code>&quot;bbbaaa&quot;</code> &rarr; <code>&quot;bbbba&quot;</code> &rarr; <code>&quot;cbba&quot;</code> &rarr; <code>&quot;cca&quot;</code> &rarr; <code>&quot;da&quot;</code>.</li>
	<li><code>nums[2] = 1</code>: No transformation can be applied, so the result is <code>&quot;a&quot;</code>.</li>
	<li>Therefore, <code>ans = [&quot;ba&quot;, &quot;da&quot;, &quot;a&quot;]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Binary Decomposition

Since two adjacent identical letters merge into the next letter of the alphabet, the letter $\texttt{'a'} + j$ is equivalent to $2^j$ copies of $\texttt{'a'}$. In other words, the strings reachable from $x$ copies of $\texttt{'a'}$ are exactly those whose letter weights sum to $x$.

To maximize the lexicographical order, we greedily use the heaviest letters first. The largest letter is $\texttt{'z'}$ with weight $2^{25}$, so we iterate $j$ from $25$ down to $0$, append $t = \left\lfloor x / 2^j \right\rfloor$ copies of the letter $\texttt{'a'} + j$ to the answer, and set $x \leftarrow x \bmod 2^j$.

Note that $t \in \{0, 1\}$ whenever $j \lt 25$, so only $\texttt{'z'}$ can appear consecutively in the answer, and $\texttt{"zz"}$ cannot be merged any further. Therefore the resulting string is valid and lexicographically largest.

The time complexity is $O(n \times \log M)$, and the space complexity is $O(\log M)$. Here, $n$ is the length of the array $\textit{nums}$, and $M$ is the maximum value in the array $\textit{nums}$. The space for the answer is not counted.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestString(self, nums: List[int]) -> List[str]:
        ans = []
        for x in nums:
            s = []
            for j in range(25, -1, -1):
                t = x >> j
                s.append(chr(ord('a') + j) * t)
                x &= (1 << j) - 1
            ans.append(''.join(s))
        return ans
```

#### Java

```java
class Solution {
    public String[] largestString(int[] nums) {
        int n = nums.length;
        String[] ans = new String[n];
        for (int k = 0; k < n; ++k) {
            int x = nums[k];
            StringBuilder s = new StringBuilder();
            for (int j = 25; j >= 0; --j) {
                for (int t = x >> j; t > 0; --t) {
                    s.append((char) ('a' + j));
                }
                x &= (1 << j) - 1;
            }
            ans[k] = s.toString();
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<string> largestString(vector<int>& nums) {
        vector<string> ans;
        ans.reserve(nums.size());
        for (int x : nums) {
            string s;
            for (int j = 25; j >= 0; --j) {
                for (int t = x >> j; t > 0; --t) {
                    s.push_back('a' + j);
                }
                x &= (1 << j) - 1;
            }
            ans.push_back(s);
        }
        return ans;
    }
};
```

#### Go

```go
func largestString(nums []int) []string {
	ans := make([]string, 0, len(nums))
	for _, x := range nums {
		s := []byte{}
		for j := 25; j >= 0; j-- {
			for t := x >> j; t > 0; t-- {
				s = append(s, byte('a'+j))
			}
			x &= (1 << j) - 1
		}
		ans = append(ans, string(s))
	}
	return ans
}
```

#### TypeScript

```ts
function largestString(nums: number[]): string[] {
    const ans: string[] = [];
    for (let x of nums) {
        const s: string[] = [];
        for (let j = 25; j >= 0; --j) {
            const t = x >> j;
            s.push(String.fromCharCode(97 + j).repeat(t));
            x &= (1 << j) - 1;
        }
        ans.push(s.join(''));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

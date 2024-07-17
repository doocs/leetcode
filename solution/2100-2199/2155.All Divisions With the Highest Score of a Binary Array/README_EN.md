---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2155.All%20Divisions%20With%20the%20Highest%20Score%20of%20a%20Binary%20Array/README_EN.md
rating: 1390
source: Weekly Contest 278 Q2
tags:
    - Array
---

<!-- problem:start -->

# [2155. All Divisions With the Highest Score of a Binary Array](https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array)

[中文文档](/solution/2100-2199/2155.All%20Divisions%20With%20the%20Highest%20Score%20of%20a%20Binary%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> binary array <code>nums</code> of length <code>n</code>. <code>nums</code> can be divided at index <code>i</code> (where <code>0 &lt;= i &lt;= n)</code> into two arrays (possibly empty) <code>nums<sub>left</sub></code> and <code>nums<sub>right</sub></code>:</p>

<ul>
	<li><code>nums<sub>left</sub></code> has all the elements of <code>nums</code> between index <code>0</code> and <code>i - 1</code> <strong>(inclusive)</strong>, while <code>nums<sub>right</sub></code> has all the elements of nums between index <code>i</code> and <code>n - 1</code> <strong>(inclusive)</strong>.</li>
	<li>If <code>i == 0</code>, <code>nums<sub>left</sub></code> is <strong>empty</strong>, while <code>nums<sub>right</sub></code> has all the elements of <code>nums</code>.</li>
	<li>If <code>i == n</code>, <code>nums<sub>left</sub></code> has all the elements of nums, while <code>nums<sub>right</sub></code> is <strong>empty</strong>.</li>
</ul>

<p>The <strong>division score</strong> of an index <code>i</code> is the <strong>sum</strong> of the number of <code>0</code>&#39;s in <code>nums<sub>left</sub></code> and the number of <code>1</code>&#39;s in <code>nums<sub>right</sub></code>.</p>

<p>Return <em><strong>all distinct indices</strong> that have the <strong>highest</strong> possible <strong>division score</strong></em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,0]
<strong>Output:</strong> [2,4]
<strong>Explanation:</strong> Division at index
- 0: nums<sub>left</sub> is []. nums<sub>right</sub> is [0,0,<u><strong>1</strong></u>,0]. The score is 0 + 1 = 1.
- 1: nums<sub>left</sub> is [<u><strong>0</strong></u>]. nums<sub>right</sub> is [0,<u><strong>1</strong></u>,0]. The score is 1 + 1 = 2.
- 2: nums<sub>left</sub> is [<u><strong>0</strong></u>,<u><strong>0</strong></u>]. nums<sub>right</sub> is [<u><strong>1</strong></u>,0]. The score is 2 + 1 = 3.
- 3: nums<sub>left</sub> is [<u><strong>0</strong></u>,<u><strong>0</strong></u>,1]. nums<sub>right</sub> is [0]. The score is 2 + 0 = 2.
- 4: nums<sub>left</sub> is [<u><strong>0</strong></u>,<u><strong>0</strong></u>,1,<u><strong>0</strong></u>]. nums<sub>right</sub> is []. The score is 3 + 0 = 3.
Indices 2 and 4 both have the highest possible division score 3.
Note the answer [4,2] would also be accepted.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> [3]
<strong>Explanation:</strong> Division at index
- 0: nums<sub>left</sub> is []. nums<sub>right</sub> is [0,0,0]. The score is 0 + 0 = 0.
- 1: nums<sub>left</sub> is [<u><strong>0</strong></u>]. nums<sub>right</sub> is [0,0]. The score is 1 + 0 = 1.
- 2: nums<sub>left</sub> is [<u><strong>0</strong></u>,<u><strong>0</strong></u>]. nums<sub>right</sub> is [0]. The score is 2 + 0 = 2.
- 3: nums<sub>left</sub> is [<u><strong>0</strong></u>,<u><strong>0</strong></u>,<u><strong>0</strong></u>]. nums<sub>right</sub> is []. The score is 3 + 0 = 3.
Only index 3 has the highest possible division score 3.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> Division at index
- 0: nums<sub>left</sub> is []. nums<sub>right</sub> is [<u><strong>1</strong></u>,<u><strong>1</strong></u>]. The score is 0 + 2 = 2.
- 1: nums<sub>left</sub> is [1]. nums<sub>right</sub> is [<u><strong>1</strong></u>]. The score is 0 + 1 = 1.
- 2: nums<sub>left</sub> is [1,1]. nums<sub>right</sub> is []. The score is 0 + 0 = 0.
Only index 0 has the highest possible division score 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

We start from $i = 0$, using two variables $\textit{l0}$ and $\textit{r1}$ to respectively record the number of $1$s to the left and right of $i$. Initially, $\textit{l0} = 0$, while $\textit{r1} = \sum \textit{nums}$.

We iterate through the array $\textit{nums}$. For each $i$, we update $\textit{l0}$ and $\textit{r1}$, calculate the current grouping score $t = \textit{l0} + \textit{r1}$. If $t$ equals the current maximum grouping score $\textit{mx}$, then we add $i$ to the answer array. If $t$ is greater than $\textit{mx}$, we update $\textit{mx}$ to $t$, clear the answer array, and then add $i$ to the answer array.

After the iteration ends, we return the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScoreIndices(self, nums: List[int]) -> List[int]:
        l0, r1 = 0, sum(nums)
        mx = r1
        ans = [0]
        for i, x in enumerate(nums, 1):
            l0 += x ^ 1
            r1 -= x
            t = l0 + r1
            if mx == t:
                ans.append(i)
            elif mx < t:
                mx = t
                ans = [i]
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> maxScoreIndices(int[] nums) {
        int l0 = 0, r1 = Arrays.stream(nums).sum();
        int mx = r1;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 1; i <= nums.length; ++i) {
            int x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            int t = l0 + r1;
            if (mx == t) {
                ans.add(i);
            } else if (mx < t) {
                mx = t;
                ans.clear();
                ans.add(i);
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
    vector<int> maxScoreIndices(vector<int>& nums) {
        int l0 = 0, r1 = accumulate(nums.begin(), nums.end(), 0);
        int mx = r1;
        vector<int> ans = {0};
        for (int i = 1; i <= nums.size(); ++i) {
            int x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            int t = l0 + r1;
            if (mx == t) {
                ans.push_back(i);
            } else if (mx < t) {
                mx = t;
                ans = {i};
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxScoreIndices(nums []int) []int {
	l0, r1 := 0, 0
	for _, x := range nums {
		r1 += x
	}
	mx := r1
	ans := []int{0}
	for i, x := range nums {
		l0 += x ^ 1
		r1 -= x
		t := l0 + r1
		if mx == t {
			ans = append(ans, i+1)
		} else if mx < t {
			mx = t
			ans = []int{i + 1}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxScoreIndices(nums: number[]): number[] {
    const n = nums.length;
    let [l0, r1] = [0, nums.reduce((a, b) => a + b, 0)];
    let mx = r1;
    const ans: number[] = [0];
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        l0 += x ^ 1;
        r1 -= x;
        const t = l0 + r1;
        if (mx === t) {
            ans.push(i);
        } else if (mx < t) {
            mx = t;
            ans.length = 0;
            ans.push(i);
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_score_indices(nums: Vec<i32>) -> Vec<i32> {
        let mut l0 = 0;
        let mut r1: i32 = nums.iter().sum();
        let mut mx = r1;
        let mut ans = vec![0];

        for i in 1..=nums.len() {
            let x = nums[i - 1];
            l0 += x ^ 1;
            r1 -= x;
            let t = l0 + r1;
            if mx == t {
                ans.push(i as i32);
            } else if mx < t {
                mx = t;
                ans = vec![i as i32];
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

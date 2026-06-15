---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3960.Frequency%20Balance%20Subarray/README_EN.md
---

<!-- problem:start -->

# [3960. Frequency Balance Subarray](https://leetcode.com/problems/frequency-balance-subarray)

[中文文档](/solution/3900-3999/3960.Frequency%20Balance%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array ​​​​​​​<code>nums</code>.</p>

<p>Define a <strong>frequency balance <span data-keyword="subarray-nonempty">subarray</span></strong> as follows:</p>

<ul>
	<li>If the subarray contains only one distinct value, it is frequency balanced.</li>
	<li>Otherwise, there must exist a positive integer <code>f</code> such that every distinct value in the subarray occurs either <code>f</code> or <code>2 * f</code> times, and both <span data-keyword="frequency-array">frequencies</span> occur among the distinct values.</li>
</ul>

<p>Return an integer denoting the length of the <strong>longest</strong> frequency balance subarray.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,1,2,3,3,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest frequency balance subarray is <code>[2, 1, 2, 3, 3]</code>.</li>
	<li>The elements that appear most frequently are 2 and 3, both appearing twice.</li>
	<li>The remaining element 1 appears once, meeting the requirements.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The longest frequency balance subarray is <code>[5, 5, 5, 5]</code>.</li>
	<li>The element that appears most frequently is 5.</li>
	<li>There are no other elements meeting the requirements.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Since all elements appear only once, the length of the longest frequency balance subarray is 1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>​​​​​​​3</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>​​​​​​​9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Hash Table

We can enumerate the left endpoint $l$ of the subarray in the range $[0, n)$, then enumerate the right endpoint $r$ from left to right starting from $l$. During the enumeration, we use two hash tables $\textit{cnt}$ and $\textit{freq}$ to record the frequency of each element in the subarray and the frequency of each frequency value, respectively.

When either of the following conditions is satisfied, update the answer $\textit{ans} = \max(\textit{ans}, r - l + 1)$:

- There is only one distinct element in the hash table $\textit{cnt}$, i.e., the length of $\textit{cnt}$ is $1$;
- There are only two distinct frequency values in the hash table $\textit{freq}$, i.e., the length of $\textit{freq}$ is $2$, and one frequency value is exactly twice the other;

After the enumeration ends, return the answer $\textit{ans}$.

The time complexity is $O(n^2)$ and the space complexity is $O(n)$. Where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getLength(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 1
        for l in range(n):
            cnt = Counter()
            freq = Counter()
            for r in range(l, n):
                x = nums[r]
                if freq[cnt[x]]:
                    freq[cnt[x]] -= 1
                    if freq[cnt[x]] == 0:
                        freq.pop(cnt[x])
                cnt[x] += 1
                freq[cnt[x]] += 1
                if (len(cnt) == 1) or (len(freq) == 2 and (freq[cnt[x] * 2] or (cnt[x] % 2 == 0 and freq[cnt[x] // 2]))):
                    ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int getLength(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for (int l = 0; l < n; l++) {
            Map<Integer, Integer> cnt = new HashMap<>();
            Map<Integer, Integer> freq = new HashMap<>();
            for (int r = l; r < n; r++) {
                int x = nums[r];
                int c = cnt.getOrDefault(x, 0);
                if (freq.getOrDefault(c, 0) > 0) {
                    freq.put(c, freq.get(c) - 1);
                    if (freq.get(c) == 0) {
                        freq.remove(c);
                    }
                }
                cnt.put(x, c + 1);
                freq.merge(cnt.get(x), 1, Integer::sum);
                int cx = cnt.get(x);
                if (cnt.size() == 1
                    || (freq.size() == 2
                        && (freq.getOrDefault(cx * 2, 0) > 0
                            || (cx % 2 == 0 && freq.getOrDefault(cx / 2, 0) > 0)))) {
                    ans = Math.max(ans, r - l + 1);
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
    int getLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 1;

        for (int l = 0; l < n; ++l) {
            unordered_map<int, int> cnt;
            unordered_map<int, int> freq;

            for (int r = l; r < n; ++r) {
                int x = nums[r];
                int c = cnt[x];

                if (freq.contains(c)) {
                    if (--freq[c] == 0) {
                        freq.erase(c);
                    }
                }

                ++cnt[x];
                ++freq[cnt[x]];

                if (cnt.size() == 1 || (freq.size() == 2 && (freq.contains(cnt[x] * 2) || (cnt[x] % 2 == 0 && freq.contains(cnt[x] / 2))))) {
                    ans = max(ans, r - l + 1);
                }
            }
        }

        return ans;
    }
};
```

#### Go

```go
func getLength(nums []int) int {
	n := len(nums)
	ans := 1
	for l := 0; l < n; l++ {
		cnt := make(map[int]int)
		freq := make(map[int]int)
		for r := l; r < n; r++ {
			x := nums[r]
			c := cnt[x]
			if freq[c] > 0 {
				freq[c]--
				if freq[c] == 0 {
					delete(freq, c)
				}
			}
			cnt[x] = c + 1
			freq[cnt[x]]++
			cx := cnt[x]
			if len(cnt) == 1 || (len(freq) == 2 && (freq[cx*2] > 0 || (cx%2 == 0 && freq[cx/2] > 0))) {
				ans = max(ans, r-l+1)
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function getLength(nums: number[]): number {
    const n = nums.length;
    let ans = 1;

    for (let l = 0; l < n; l++) {
        const cnt = new Map<number, number>();
        const freq = new Map<number, number>();

        for (let r = l; r < n; r++) {
            const x = nums[r];
            const c = cnt.get(x) ?? 0;

            if ((freq.get(c) ?? 0) > 0) {
                const f = (freq.get(c) ?? 0) - 1;
                if (f === 0) {
                    freq.delete(c);
                } else {
                    freq.set(c, f);
                }
            }

            cnt.set(x, c + 1);
            freq.set(c + 1, (freq.get(c + 1) ?? 0) + 1);

            const cur = c + 1;

            if (
                cnt.size === 1 ||
                (freq.size === 2 &&
                    ((freq.get(cur * 2) ?? 0) > 0 ||
                        (cur % 2 === 0 && (freq.get(cur / 2) ?? 0) > 0)))
            ) {
                ans = Math.max(ans, r - l + 1);
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

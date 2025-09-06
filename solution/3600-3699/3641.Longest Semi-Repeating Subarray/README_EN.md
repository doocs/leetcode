---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3641.Longest%20Semi-Repeating%20Subarray/README_EN.md
---

<!-- problem:start -->

# [3641. Longest Semi-Repeating Subarray 🔒](https://leetcode.com/problems/longest-semi-repeating-subarray)

[中文文档](/solution/3600-3699/3641.Longest%20Semi-Repeating%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>A <strong>semi‑repeating</strong> subarray is a contiguous subarray in which at most <code>k</code> elements repeat (i.e., appear more than once).</p>

<p>Return the length of the longest <strong>semi‑repeating</strong> subarray in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,1,2,3,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest semi-repeating subarray is <code>[2, 3, 1, 2, 3, 4]</code>, which has two repeating elements (2 and 3).</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1,1], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest semi-repeating subarray is <code>[1, 1, 1, 1, 1]</code>, which has only one repeating element (1).</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1,1], k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest semi-repeating subarray is <code>[1]</code>, which has no repeating elements.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>
<style type="text/css">.spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px 0px; font-size:150%; font-weight: bold; color:#000000; background-color:cyan; outline:0;
}
.spoiler {overflow:hidden;}
.spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s ease;-o-transition: all 0s ease;transition: margin 0s ease;}
.spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-2000%;}
.spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
</style>
<input class="spoilerbutton" onclick="this.value=this.value=='Show Message'?'Hide Message':'Show Message';" type="button" value="Show Message" />
<div class="spoiler">
<div>
<p><strong>FOR TESTING ONLY. WILL BE DELETED LATER.</strong></p>
// Model solution has runtime of O(n log n), O(n*n) and above should TLE.

<pre>
# Bromelia

import sys
import random, json, string
import math
import datetime
from collections import defaultdict
ri = random.randint

MAX_N   = 100_000
MAX_VAL = 100_000

def randomString(n, allowed):
    return &#39;&#39;.join(random.choices(allowed, k=n))

def randomUnique(x, y, n):
    return random.sample(range(x, y + 1), n)

def randomArray(x, y, n):
    return [ri(x, y) for _ in range(n)]

def shuffle(arr):
    random.shuffle(arr)
    return arr

def pr(a):
    file.write(str(a).replace(&quot; &quot;, &quot;&quot;).replace(&quot;\&#39;&quot;, &quot;\&quot;&quot;).replace(&quot;\&quot;null\&quot;&quot;, &quot;null&quot;) + &#39;\n&#39;)

def prstr(a):
    pr(&quot;\&quot;&quot; + a + &quot;\&quot;&quot;)


def prtc(tc):
    nums, k = tc
    pr(nums)
    pr(k)
    
def examples():
    yield ([1, 2, 3, 1, 2, 3, 4], 2)
    yield ([1, 1, 1, 1, 1], 4)
    yield ([1, 1, 1, 1, 1], 0)

def smallCases():
    yield ([MAX_VAL], 0)
    yield ([MAX_VAL], 1)

    for len in range(1, 3 + 1):
        nums = [0] * len

        def recursiveGenerate(idx: int):
            if idx == len:
                for k in range(0, len + 1):
                    yield (nums, k)
            else:
                for nextElement in range(1, len + 1):
                    nums[idx] = nextElement
                    yield from recursiveGenerate(idx + 1)

        yield from recursiveGenerate(0)

def randomCases():
    params = [
        (    4,    20,      10, 400),
        (   21,  2000,    1000, 100),
        (MAX_N, MAX_N,      10,   2),
        (MAX_N, MAX_N,     500,   2),
        (MAX_N, MAX_N, MAX_VAL,   2),
    ]
    for minLen, maxLen, maxVal, testCount in params:
        for _ in range(testCount):
            len = ri(minLen, maxLen)
            k = ri(1, len)

            nums = [0] * len
            for i in range(len):
                nums[i] = ri(1, maxVal)        

            yield (nums, k)

def cornerCases():
    yield ([MAX_VAL] * MAX_N, 0)
    yield ([MAX_VAL] * MAX_N, MAX_N)
    yield ([i for i in range(1, MAX_N + 1)], 0)
    yield ([i for i in range(1, MAX_N + 1)], MAX_N)
    yield ([i // 2 + 1 for i in range(MAX_N)], MAX_N // 2 - 1)
    yield ([i % (MAX_N // 2) + 1 for i in range(MAX_N)], MAX_N // 2 - 1)


with open(&#39;test.txt&#39;, &#39;w&#39;) as file:
    random.seed(0)
    for tc in examples(): prtc(tc)
    for tc in smallCases(): prtc(tc)
    for tc in sorted(list(randomCases()), key = lambda x: len(x[0])): prtc(tc)
    for tc in cornerCases(): prtc(tc)
</pre>
</div>
</div>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window

We use two pointers $l$ and $r$ to maintain a sliding window, where the right pointer continuously moves to the right, and we use a hash table $\textit{cnt}$ to record the number of occurrences of each element within the current window.

When the occurrence count of an element changes from $1$ to $2$, it indicates that there is a new repeating element, so we increment the repeating element counter $\textit{cur}$ by $1$. When the repeating element counter exceeds $k$, it means the current window does not satisfy the condition, and we need to move the left pointer until the repeating element counter is no greater than $k$. During the process of moving the left pointer, if the occurrence count of an element changes from $2$ to $1$, it indicates that there is one less repeating element, so we decrement the repeating element counter by $1$. Then, we update the answer, i.e., $\textit{ans} = \max(\textit{ans}, r - l + 1)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        ans = cur = l = 0
        for r, x in enumerate(nums):
            cnt[x] += 1
            cur += cnt[x] == 2
            while cur > k:
                cnt[nums[l]] -= 1
                cur -= cnt[nums[l]] == 1
                l += 1
            ans = max(ans, r - l + 1)
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, cur = 0, l = 0;
        for (int r = 0; r < nums.length; ++r) {
            if (cnt.merge(nums[r], 1, Integer::sum) == 2) {
                ++cur;
            }
            while (cur > k) {
                if (cnt.merge(nums[l++], -1, Integer::sum) == 1) {
                    --cur;
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0, cur = 0, l = 0;
        for (int r = 0; r < nums.size(); ++r) {
            if (++cnt[nums[r]] == 2) {
                ++cur;
            }
            while (cur > k) {
                if (--cnt[nums[l++]] == 1) {
                    --cur;
                }
            }
            ans = max(ans, r - l + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int, k int) (ans int) {
	cnt := make(map[int]int)
	cur, l := 0, 0
	for r := 0; r < len(nums); r++ {
		if cnt[nums[r]]++; cnt[nums[r]] == 2 {
			cur++
		}
		for cur > k {
			if cnt[nums[l]]--; cnt[nums[l]] == 1 {
				cur--
			}
			l++
		}
		ans = max(ans, r-l+1)
	}
	return
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let [ans, cur, l] = [0, 0, 0];
    for (let r = 0; r < nums.length; r++) {
        cnt.set(nums[r], (cnt.get(nums[r]) || 0) + 1);
        if (cnt.get(nums[r]) === 2) {
            cur++;
        }

        while (cur > k) {
            cnt.set(nums[l], cnt.get(nums[l])! - 1);
            if (cnt.get(nums[l]) === 1) {
                cur--;
            }
            l++;
        }

        ans = Math.max(ans, r - l + 1);
    }

    return ans;
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn longest_subarray(nums: Vec<i32>, k: i32) -> i32 {
        let mut cnt = HashMap::new();
        let mut ans = 0;
        let mut cur = 0;
        let mut l = 0;

        for r in 0..nums.len() {
            let entry = cnt.entry(nums[r]).or_insert(0);
            *entry += 1;
            if *entry == 2 {
                cur += 1;
            }

            while cur > k {
                let entry = cnt.entry(nums[l]).or_insert(0);
                *entry -= 1;
                if *entry == 1 {
                    cur -= 1;
                }
                l += 1;
            }

            ans = ans.max(r - l + 1);
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

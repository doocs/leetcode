---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2899.Last%20Visited%20Integers/README_EN.md
rating: 1372
source: Biweekly Contest 115 Q1
tags:
    - Array
    - Simulation
---

# [2899. Last Visited Integers](https://leetcode.com/problems/last-visited-integers)

[中文文档](/solution/2800-2899/2899.Last%20Visited%20Integers/README.md)

## Description

<p>Given an integer array <code>nums</code> where <code>nums[i]</code> is either a positive integer or <code>-1</code>. We need to find for each <code>-1</code> the respective positive integer, which we call the last visited integer.</p>

<p>To achieve this goal, let&#39;s define two empty arrays: <code>seen</code> and <code>ans</code>.</p>

<p>Start iterating from the beginning of the array <code>nums</code>.</p>

<ul>
	<li>If a positive integer is encountered, prepend it to the <strong>front</strong> of <code>seen</code>.</li>
	<li>If <code>-1</code>&nbsp;is encountered, let <code>k</code> be the number of <strong>consecutive</strong> <code>-1</code>s seen so far (including the current <code>-1</code>),
	<ul>
		<li>If <code>k</code> is less than or equal to the length of <code>seen</code>, append the <code>k</code>-th element of <code>seen</code> to <code>ans</code>.</li>
		<li>If <code>k</code> is strictly greater than the length of <code>seen</code>, append <code>-1</code> to <code>ans</code>.</li>
	</ul>
	</li>
</ul>

<p>Return the array<em> </em><code>ans</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,-1,-1,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Start with <code>seen = []</code> and <code>ans = []</code>.</p>

<ol>
	<li>Process <code>nums[0]</code>: The first element in nums is <code>1</code>. We prepend it to the front of <code>seen</code>. Now, <code>seen == [1]</code>.</li>
	<li>Process <code>nums[1]</code>: The next element is <code>2</code>. We prepend it to the front of <code>seen</code>. Now, <code>seen == [2, 1]</code>.</li>
	<li>Process <code>nums[2]</code>: The next element is <code>-1</code>. This is the first occurrence of <code>-1</code>, so <code>k == 1</code>. We look for the first element in seen. We append <code>2</code> to <code>ans</code>. Now, <code>ans == [2]</code>.</li>
	<li>Process <code>nums[3]</code>: Another <code>-1</code>. This is the second consecutive <code>-1</code>, so <code>k == 2</code>. The second element in <code>seen</code> is <code>1</code>, so we append <code>1</code> to <code>ans</code>. Now, <code>ans == [2, 1]</code>.</li>
	<li>Process <code>nums[4]</code>: Another <code>-1</code>, the third in a row, making <code>k = 3</code>. However, <code>seen</code> only has two elements (<code>[2, 1]</code>). Since <code>k</code> is greater than the number of elements in <code>seen</code>, we append <code>-1</code> to <code>ans</code>. Finally, <code>ans == [2, 1, -1]</code>.</li>
</ol>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,2,-1,-1]</span></p>

<p><strong>Output:</strong><span class="example-io"> [1,2,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>Start with <code>seen = []</code> and <code>ans = []</code>.</p>

<ol>
	<li>Process <code>nums[0]</code>: The first element in nums is <code>1</code>. We prepend it to the front of <code>seen</code>. Now, <code>seen == [1]</code>.</li>
	<li>Process <code>nums[1]</code>: The next element is <code>-1</code>. This is the first occurrence of <code>-1</code>, so <code>k == 1</code>. We look for the first element in <code>seen</code>, which is <code>1</code>. Append <code>1</code> to <code>ans</code>. Now, <code>ans == [1]</code>.</li>
	<li>Process <code>nums[2]</code>: The next element is <code>2</code>. Prepend this to the front of <code>seen</code>. Now, <code>seen == [2, 1]</code>.</li>
	<li>Process <code>nums[3]</code>: The next element is <code>-1</code>. This <code>-1</code> is not consecutive to the first <code>-1</code> since <code>2</code> was in between. Thus, <code>k</code> resets to <code>1</code>. The first element in <code>seen</code> is <code>2</code>, so append <code>2</code> to <code>ans</code>. Now, <code>ans == [1, 2]</code>.</li>
	<li>Process <code>nums[4]</code>: Another <code>-1</code>. This is consecutive to the previous <code>-1</code>, so <code>k == 2</code>. The second element in <code>seen</code> is <code>1</code>, append <code>1</code> to <code>ans</code>. Finally, <code>ans == [1, 2, 1]</code>.</li>
</ol>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums[i] == -1</code> or <code>1 &lt;= nums[i]&nbsp;&lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Simulation

We can directly simulate according to the problem statement. In the implementation, we use an array $nums$ to store the traversed integers, and an integer $k$ to record the current number of consecutive $prev$ strings. If the current string is $prev$, we take out the $|nums| - k-th$ integer from $nums$. If it does not exist, we return $-1$.

The time complexity is $O(n)$, where $n$ is the length of the array $words$. The space complexity is $O(n)$.

<!-- tabs:start -->

```python
class Solution:
    def lastVisitedIntegers(self, words: List[str]) -> List[int]:
        nums = []
        ans = []
        k = 0
        for w in words:
            if w == "prev":
                k += 1
                i = len(nums) - k
                ans.append(-1 if i < 0 else nums[i])
            else:
                k = 0
                nums.append(int(w))
        return ans
```

```java
class Solution {
    public List<Integer> lastVisitedIntegers(List<String> words) {
        List<Integer> nums = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        for (var w : words) {
            if ("prev".equals(w)) {
                ++k;
                int i = nums.size() - k;
                ans.add(i < 0 ? -1 : nums.get(i));
            } else {
                k = 0;
                nums.add(Integer.valueOf(w));
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> lastVisitedIntegers(vector<string>& words) {
        vector<int> nums;
        vector<int> ans;
        int k = 0;
        for (auto& w : words) {
            if (w == "prev") {
                ++k;
                int i = nums.size() - k;
                ans.push_back(i < 0 ? -1 : nums[i]);
            } else {
                k = 0;
                nums.push_back(stoi(w));
            }
        }
        return ans;
    }
};
```

```go
func lastVisitedIntegers(words []string) (ans []int) {
	nums := []int{}
	k := 0
	for _, w := range words {
		if w == "prev" {
			k++
			i := len(nums) - k
			if i < 0 {
				ans = append(ans, -1)
			} else {
				ans = append(ans, nums[i])
			}
		} else {
			k = 0
			x, _ := strconv.Atoi(w)
			nums = append(nums, x)
		}
	}
	return
}
```

```ts
function lastVisitedIntegers(words: string[]): number[] {
    const nums: number[] = [];
    const ans: number[] = [];
    let k = 0;
    for (const w of words) {
        if (w === 'prev') {
            ++k;
            const i = nums.length - k;
            ans.push(i < 0 ? -1 : nums[i]);
        } else {
            k = 0;
            nums.push(+w);
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn last_visited_integers(words: Vec<String>) -> Vec<i32> {
        let mut nums: Vec<i32> = Vec::new();
        let mut ans: Vec<i32> = Vec::new();
        let mut k = 0;

        for w in words {
            if w == "prev" {
                k += 1;
                let i = (nums.len() as i32) - k;
                ans.push(if i < 0 { -1 } else { nums[i as usize] });
            } else {
                k = 0;
                nums.push(w.parse::<i32>().unwrap());
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->

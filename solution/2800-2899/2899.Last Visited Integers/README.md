# [2899. 上一个遍历的整数](https://leetcode.cn/problems/last-visited-integers)

[English Version](/solution/2800-2899/2899.Last%20Visited%20Integers/README_EN.md)

<!-- tags:数组,模拟 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;要么是一个正整数，要么是&nbsp;<code>-1</code>&nbsp;。</p>

<p>我们需要为每个 <code>-1</code> 找到相应的正整数，我们称之为最后访问的整数。</p>

<p>为了达到这个目标，定义两个空数组：<code>seen</code>&nbsp;和&nbsp;<code>ans</code>。</p>

<p>从数组&nbsp;<code>nums</code>&nbsp;的头部开始遍历。</p>

<ul>
	<li>如果遇到正整数，把它添加到&nbsp;<code>seen</code>&nbsp;的&nbsp;<strong>头部</strong>。</li>
	<li>如果遇到 <code>-1</code>，则设 <code>k</code> 是到目前为止看到的 <strong>连续</strong> <code>-1</code> 的数目(包括当前 <code>-1</code>)，
	<ul>
		<li>如果&nbsp;<code>k</code>&nbsp;小于等于&nbsp;<code>seen</code>&nbsp;的长度，把&nbsp;<code>seen</code>&nbsp;的第&nbsp;<code>k</code>&nbsp;个元素添加到&nbsp;<code>ans</code>。</li>
		<li>如果&nbsp;<code>k</code>&nbsp;严格大于&nbsp;<code>seen</code>&nbsp;的长度，把&nbsp;<code>-1</code>&nbsp;添加到&nbsp;<code>ans</code>。</li>
	</ul>
	</li>
</ul>

<p>请你返回数组&nbsp;<code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,-1,-1,-1]
<b>输出：</b>[2,1,-1]
<b>解释：</b> 开始时 seen = [] 且 ans = []。
1.处理 nums[0]：nums 中的第一个元素是 1。我们将其放在 seen 的前面。现在，seen == [1]。
2.处理 nums[1]：下一个元素是 2。我们将其放在 seen 的前面。现在，seen == [2, 1]。
3.处理 nums[2]：下一个元素是 -1。这是 -1 的第一次出现，所以 k == 1。我们找到 seen 中的第一个元素，把 2 添加到 ans。现在，ans == [2]。
4.处理 nums[3]：又一个 -1。这是 -1 的第二次出现，所以 k == 2。seen 中的第二个元素是 1，所以我们把 1 添加到 ans。现在，ans == [2, 1]。
5.处理 nums[4]：又一个 -1。第三次出现，让 k = 3。然而，seen 中只有两个元素（[2, 1]）。因为 k 比 seen 中的元素数量更大，我们把 -1 添加到 ans。最终，ans == [2, 1, -1]。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,-1,2,-1,-1]
<b>输出：</b>[1,2,1]
<strong>解释：</strong> 开始时 seen = [] 且 ans = []。
1.处理 nums[0]：nums 中的第一个元素是 1。我们将其放在 seen 的前面。现在，seen == [1]。
2.处理 nums[1]：下一个元素是 -1。这是 -1 的第一次出现，所以 k == 1。我们找到 seen 中的第一个元素，即 1。把 1 添加到 ans。现在，ans == [1]。
3.处理 nums[2]：下一个元素是 2。我们将其放在 seen 的前面。现在，seen == [2, 1]。
4.处理 nums[3]：下一个元素是 -1。这个 -1 与 第一个 -1 不连续，因为中间有个 2。因此，k 重置为 1。seen 中的第一个元素是 2，所以我们把 2 添加到 ans。现在，ans == [2, 2]。
5.处理 nums[4]：又一个 -1。它与前一个 -1 相邻，所以 k == 2。seen 中的第 2 个元素是 1。把 1 添加到 ans。最终，ans == [1, 2, 1]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums[i] == -1</code>&nbsp;或&nbsp;<code>1 &lt;= nums[i]&nbsp;&lt;= 100</code></li>
</ul>

## 解法

### 方法一：模拟

我们直接根据题意模拟即可。在实现上，我们使用一个数组 $nums$ 来存储遍历过的整数，使用一个整数 $k$ 来记录当前连续的 $prev$ 字符串数目。如果当前字符串是 $prev$，那么我们就从 $nums$ 中取出第 $|nums| - k$ 个整数，如果不存在，那么就返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $words$ 的长度。

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

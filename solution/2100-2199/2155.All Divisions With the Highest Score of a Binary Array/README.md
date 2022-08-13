# [2155. 分组得分最高的所有下标](https://leetcode.cn/problems/all-divisions-with-the-highest-score-of-a-binary-array)

[English Version](/solution/2100-2199/2155.All%20Divisions%20With%20the%20Highest%20Score%20of%20a%20Binary%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的二进制数组 <code>nums</code> ，数组长度为 <code>n</code> 。<code>nums</code> 可以按下标 <code>i</code>（ <code>0 &lt;= i &lt;= n</code> ）拆分成两个数组（可能为空）：<code>nums<sub>left</sub></code> 和 <code>nums<sub>right</sub></code> 。</p>

<ul>
	<li><code>nums<sub>left</sub></code> 包含 <code>nums</code> 中从下标 <code>0</code> 到 <code>i - 1</code> 的所有元素<strong>（包括 </strong><code>0</code><strong> 和 </strong><code>i - 1</code><strong> ）</strong>，而 <code>nums<sub>right</sub></code> 包含 <code>nums</code> 中从下标 <code>i</code> 到 <code>n - 1</code> 的所有元素<strong>（包括 </strong><code>i</code><strong> 和 </strong><code>n - 1</code><strong> ）。</strong></li>
	<li>如果 <code>i == 0</code> ，<code>nums<sub>left</sub></code> 为 <strong>空</strong> ，而 <code>nums<sub>right</sub></code> 将包含 <code>nums</code> 中的所有元素。</li>
	<li>如果 <code>i == n</code> ，<code>nums<sub>left</sub></code> 将包含 <code>nums</code> 中的所有元素，而 <code>nums<sub>right</sub></code> 为 <strong>空</strong> 。</li>
</ul>

<p>下标 <code>i</code><strong> </strong>的<strong> 分组得分</strong> 为 <code>nums<sub>left</sub></code> 中 <code>0</code> 的个数和 <code>nums<sub>right</sub></code> 中 <code>1</code> 的个数之<strong> 和</strong> 。</p>

<p>返回 <strong>分组得分 最高</strong> 的 <strong>所有不同下标</strong> 。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [0,0,1,0]
<strong>输出：</strong>[2,4]
<strong>解释：</strong>按下标分组
- 0 ：nums<sub>left</sub> 为 [] 。nums<sub>right</sub> 为 [0,0,<em><strong>1</strong></em>,0] 。得分为 0 + 1 = 1 。
- 1 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [0,<em><strong>1</strong></em>,0] 。得分为 1 + 1 = 2 。
- 2 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [<em><strong>1</strong></em>,0] 。得分为 2 + 1 = 3 。
- 3 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>,1] 。nums<sub>right</sub> 为 [0] 。得分为 2 + 0 = 2 。
- 4 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>,1,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [] 。得分为 3 + 0 = 3 。
下标 2 和 4 都可以得到最高的分组得分 3 。
注意，答案 [4,2] 也被视为正确答案。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>[3]
<strong>解释：</strong>按下标分组
- 0 ：nums<sub>left</sub> 为 [] 。nums<sub>right</sub> 为 [0,0,0] 。得分为 0 + 0 = 0 。
- 1 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [0,0] 。得分为 1 + 0 = 1 。
- 2 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [0] 。得分为 2 + 0 = 2 。
- 3 ：nums<sub>left</sub> 为 [<em><strong>0</strong></em>,<em><strong>0</strong></em>,<em><strong>0</strong></em>] 。nums<sub>right</sub> 为 [] 。得分为 3 + 0 = 3 。
只有下标 3 可以得到最高的分组得分 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[0]
<strong>解释：</strong>按下标分组
- 0 ：nums<sub>left</sub> 为 [] 。nums<sub>right</sub> 为 [<em><strong>1</strong></em>,<em><strong>1</strong></em>] 。得分为 0 + 2 = 2 。
- 1 ：nums<sub>left</sub> 为 [1] 。nums<sub>right</sub> 为 [<em><strong>1</strong></em>] 。得分为 0 + 1 = 1 。
- 2 ：nums<sub>left</sub> 为 [1,1] 。nums<sub>right</sub> 为 [] 。得分为 0 + 0 = 0 。
只有下标 0 可以得到最高的分组得分 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScoreIndices(self, nums: List[int]) -> List[int]:
        left, right = 0, sum(nums)
        mx = right
        ans = [0]
        for i, num in enumerate(nums):
            if num == 0:
                left += 1
            else:
                right -= 1
            t = left + right
            if mx == t:
                ans.append(i + 1)
            elif mx < t:
                mx = t
                ans = [i + 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public List<Integer> maxScoreIndices(int[] nums) {
        int left = 0, right = sum(nums);
        int mx = right;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                ++left;
            } else {
                --right;
            }
            int t = left + right;
            if (mx == t) {
                ans.add(i + 1);
            } else if (mx < t) {
                mx = t;
                ans.clear();
                ans.add(i + 1);
            }
        }
        return ans;
    }

    private int sum(int[] nums) {
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        return s;
    }
}

```

### **TypeScript**

```ts
function maxScoreIndices(nums: number[]): number[] {
    const n = nums.length;
    const total = nums.reduce((a, c) => a + c, 0);
    let left = 0,
        right = total;
    let record: Array<number> = [total];
    for (const num of nums) {
        if (num == 0) {
            left++;
        } else {
            right--;
        }
        record.push(left + right);
    }
    const max = Math.max(...record);
    let ans: Array<number> = [];
    for (let i = 0; i <= n; i++) {
        if (record[i] == max) {
            ans.push(i);
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> maxScoreIndices(vector<int>& nums) {
        int left = 0, right = accumulate(nums.begin(), nums.end(), 0);
        int mx = right;
        vector<int> ans;
        ans.push_back(0);
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == 0)
                ++left;
            else
                --right;
            int t = left + right;
            if (mx == t)
                ans.push_back(i + 1);
            else if (mx < t) {
                mx = t;
                ans.clear();
                ans.push_back(i + 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxScoreIndices(nums []int) []int {
	left, right := 0, 0
	for _, num := range nums {
		right += num
	}
	mx := right
	ans := []int{0}
	for i, num := range nums {
		if num == 0 {
			left++
		} else {
			right--
		}
		t := left + right
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

### **...**

```

```

<!-- tabs:end -->

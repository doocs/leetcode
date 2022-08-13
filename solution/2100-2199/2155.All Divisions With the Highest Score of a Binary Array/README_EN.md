# [2155. All Divisions With the Highest Score of a Binary Array](https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array)

[中文文档](/solution/2100-2199/2155.All%20Divisions%20With%20the%20Highest%20Score%20of%20a%20Binary%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> binary array <code>nums</code> of length <code>n</code>. <code>nums</code> can be divided at index <code>i</code> (where <code>0 &lt;= i &lt;= n)</code> into two arrays (possibly empty) <code>nums<sub>left</sub></code> and <code>nums<sub>right</sub></code>:</p>

<ul>
	<li><code>nums<sub>left</sub></code> has all the elements of <code>nums</code> between index <code>0</code> and <code>i - 1</code> <strong>(inclusive)</strong>, while <code>nums<sub>right</sub></code> has all the elements of nums between index <code>i</code> and <code>n - 1</code> <strong>(inclusive)</strong>.</li>
	<li>If <code>i == 0</code>, <code>nums<sub>left</sub></code> is <strong>empty</strong>, while <code>nums<sub>right</sub></code> has all the elements of <code>nums</code>.</li>
	<li>If <code>i == n</code>, <code>nums<sub>left</sub></code> has all the elements of nums, while <code>nums<sub>right</sub></code> is <strong>empty</strong>.</li>
</ul>

<p>The <strong>division score</strong> of an index <code>i</code> is the <strong>sum</strong> of the number of <code>0</code>&#39;s in <code>nums<sub>left</sub></code> and the number of <code>1</code>&#39;s in <code>nums<sub>right</sub></code>.</p>

<p>Return <em><strong>all distinct indices</strong> that have the <strong>highest</strong> possible <strong>division score</strong></em>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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

<p><strong>Example 2:</strong></p>

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

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

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

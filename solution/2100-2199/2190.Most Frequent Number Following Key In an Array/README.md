# [2190. 数组中紧跟 key 之后出现最频繁的数字](https://leetcode.cn/problems/most-frequent-number-following-key-in-an-array)

[English Version](/solution/2100-2199/2190.Most%20Frequent%20Number%20Following%20Key%20In%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，同时给你一个整数&nbsp;<code>key</code>&nbsp;，它在&nbsp;<code>nums</code>&nbsp;出现过。</p>

<p><strong>统计&nbsp;</strong>在 <code>nums</code>&nbsp;数组中紧跟着 <code>key</code>&nbsp;后面出现的不同整数&nbsp;<code>target</code>&nbsp;的出现次数。换言之，<code>target</code>&nbsp;的出现次数为满足以下条件的 <code>i</code>&nbsp;的数目：</p>

<ul>
	<li><code>0 &lt;= i &lt;= n - 2</code></li>
	<li><code>nums[i] == key</code>&nbsp;且</li>
	<li><code>nums[i + 1] == target</code>&nbsp;。</li>
</ul>

<p>请你返回出现 <strong>最多</strong>&nbsp;次数的<em>&nbsp;</em><code>target</code>&nbsp;。测试数据保证出现次数最多的 <code>target</code>&nbsp;是唯一的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,100,200,1,100], key = 1
<b>输出：</b>100
<b>解释：</b>对于 target = 100 ，在下标 1 和 4 处出现过 2 次，且都紧跟着 key 。
没有其他整数在 key 后面紧跟着出现，所以我们返回 100 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [2,2,2,2,3], key = 2
<b>输出：</b>2
<b>解释：</b>对于 target = 2 ，在下标 1 ，2 和 3 处出现过 3 次，且都紧跟着 key 。
对于 target = 3 ，在下标 4 出出现过 1 次，且紧跟着 key 。
target = 2 是紧跟着 key 之后出现次数最多的数字，所以我们返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>测试数据保证答案是唯一的。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostFrequent(self, nums: List[int], key: int) -> int:
        cnt = Counter()
        mx = ans = 0
        for i, v in enumerate(nums[:-1]):
            if v == key:
                target = nums[i + 1]
                cnt[target] += 1
                if mx < cnt[target]:
                    mx = cnt[target]
                    ans = nums[i + 1]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int mostFrequent(int[] nums, int key) {
        int[] cnt = new int[1010];
        int mx = 0, ans = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                ++cnt[target];
                if (mx < cnt[target]) {
                    mx = cnt[target];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mostFrequent(vector<int>& nums, int key) {
        vector<int> cnt(1010);
        int mx = 0, ans = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == key) {
                int target = nums[i + 1];
                ++cnt[target];
                if (mx < cnt[target]) {
                    mx = cnt[target];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mostFrequent(nums []int, key int) int {
	cnt := make([]int, 1010)
	mx, ans := 0, 0
	for i, v := range nums[:len(nums)-1] {
		if v == key {
			target := nums[i+1]
			cnt[target]++
			if mx < cnt[target] {
				mx = cnt[target]
				ans = nums[i+1]
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $key
     * @return Integer
     */
    function mostFrequent($nums, $key) {
        $max = $maxNum = 0;
        for ($i = 0; $i < count($nums) - 1; $i++) {
            if ($nums[$i] == $key) {
                $hashtable[$nums[$i + 1]] += 1;
                $tmp = $hashtable[$nums[$i + 1]];
                if ($tmp > $max) {
                    $max = $tmp;
                    $maxNum = $nums[$i + 1];
                }
            }
        }
        return $maxNum;
    }
}
```

### **...**

```

```

<!-- tabs:end -->

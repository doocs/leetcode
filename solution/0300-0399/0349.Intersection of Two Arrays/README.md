# [349. 两个数组的交集](https://leetcode.cn/problems/intersection-of-two-arrays)

[English Version](/solution/0300-0399/0349.Intersection%20of%20Two%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code> ，返回 <em>它们的交集</em>&nbsp;。输出结果中的每个元素一定是 <strong>唯一</strong> 的。我们可以 <strong>不考虑输出结果的顺序</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,2,1], nums2 = [2,2]
<strong>输出：</strong>[2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>输出：</strong>[9,4]
<strong>解释：</strong>[4,9] 也是可通过的
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：哈希表或数组

我们先用哈希表或者一个长度为 $1001$ 的数组 $s$ 记录数组 $nums1$ 中出现的元素，然后遍历数组 $nums2$ 中每个元素，如果元素 $x$ 在 $s$ 中，那么将 $x$ 加入答案，并且从 $s$ 中移除 $x$。

遍历结束后，返回答案数组即可。

时间复杂度 $O(n+m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        return list(set(nums1) & set(nums2))
```

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        boolean[] s = new boolean[1001];
        for (int x : nums1) {
            s[x] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for (int x : nums2) {
            if (s[x]) {
                ans.add(x);
                s[x] = false;
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
```

```cpp
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        bool s[1001];
        memset(s, false, sizeof(s));
        for (int x : nums1) {
            s[x] = true;
        }
        vector<int> ans;
        for (int x : nums2) {
            if (s[x]) {
                ans.push_back(x);
                s[x] = false;
            }
        }
        return ans;
    }
};
```

```go
func intersection(nums1 []int, nums2 []int) (ans []int) {
	s := [1001]bool{}
	for _, x := range nums1 {
		s[x] = true
	}
	for _, x := range nums2 {
		if s[x] {
			ans = append(ans, x)
			s[x] = false
		}
	}
	return
}
```

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function (nums1, nums2) {
    const s = Array(1001).fill(false);
    for (const x of nums1) {
        s[x] = true;
    }
    const ans = [];
    for (const x of nums2) {
        if (s[x]) {
            ans.push(x);
            s[x] = false;
        }
    }
    return ans;
};
```

```cs
public class Solution {
    public int[] Intersection(int[] nums1, int[] nums2) {
        List<int> result = new List<int>();
        HashSet<int> arr1 = new(nums1);
        HashSet<int> arr2 = new(nums2);
        foreach (int x in arr1) {
            if (arr2.Contains(x)) {
                result.Add(x);
            }
        }
        return result.ToArray();
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums1
     * @param Integer[] $nums2
     * @return Integer[]
     */
    function intersection($nums1, $nums2) {
        $rs = [];
        $set1 = array_values(array_unique($nums1));
        $set2 = array_values(array_unique($nums2));
        for ($i = 0; $i < count($set1); $i++) {
            $hashmap[$set1[$i]] = 1;
        }
        for ($j = 0; $j < count($set2); $j++) {
            if ($hashmap[$set2[$j]]) {
                array_push($rs, $set2[$j]);
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var intersection = function (nums1, nums2) {
    return Array.from(new Set(nums1)).filter(num => new Set(nums2).has(num));
};
```

<!-- tabs:end -->

<!-- end -->

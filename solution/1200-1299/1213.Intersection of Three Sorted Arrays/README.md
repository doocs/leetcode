---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1213.Intersection%20of%20Three%20Sorted%20Arrays/README.md
rating: 1259
source: 第 10 场双周赛 Q1
tags:
    - 数组
    - 哈希表
    - 二分查找
    - 计数
---

<!-- problem:start -->

# [1213. 三个有序数组的交集 🔒](https://leetcode.cn/problems/intersection-of-three-sorted-arrays)

[English Version](/solution/1200-1299/1213.Intersection%20of%20Three%20Sorted%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给出三个均为 <strong>严格递增排列 </strong>的整数数组&nbsp;<code>arr1</code>，<code>arr2</code> 和&nbsp;<code>arr3</code>。返回一个由&nbsp;<strong>仅 </strong>在这三个数组中&nbsp;<strong>同时出现&nbsp;</strong>的整数所构成的有序数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
<strong>输出: </strong>[1,5]
<strong>解释: </strong>只有 1 和 5 同时在这三个数组中出现.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
<strong>输出: </strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length, arr3.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr1[i], arr2[i], arr3[i] &lt;= 2000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

遍历三个数组，统计每个数字出现的次数，然后遍历任意一个数组，若某个数字出现的次数为 $3$，则将其加入结果数组。

时间复杂度 $O(n)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为数组的长度和数组中数字的范围。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arraysIntersection(
        self, arr1: List[int], arr2: List[int], arr3: List[int]
    ) -> List[int]:
        cnt = Counter(arr1 + arr2 + arr3)
        return [x for x in arr1 if cnt[x] == 3]
```

#### Java

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        int[] cnt = new int[2001];
        for (int x : arr1) {
            ++cnt[x];
        }
        for (int x : arr2) {
            ++cnt[x];
        }
        for (int x : arr3) {
            if (++cnt[x] == 3) {
                ans.add(x);
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
    vector<int> arraysIntersection(vector<int>& arr1, vector<int>& arr2, vector<int>& arr3) {
        vector<int> ans;
        int cnt[2001]{};
        for (int x : arr1) {
            ++cnt[x];
        }
        for (int x : arr2) {
            ++cnt[x];
        }
        for (int x : arr3) {
            if (++cnt[x] == 3) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) (ans []int) {
	cnt := [2001]int{}
	for _, x := range arr1 {
		cnt[x]++
	}
	for _, x := range arr2 {
		cnt[x]++
	}
	for _, x := range arr3 {
		cnt[x]++
		if cnt[x] == 3 {
			ans = append(ans, x)
		}
	}
	return
}
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $arr1
     * @param Integer[] $arr2
     * @param Integer[] $arr3
     * @return Integer[]
     */
    function arraysIntersection($arr1, $arr2, $arr3) {
        $rs = [];
        $arr = array_merge($arr1, $arr2, $arr3);
        for ($i = 0; $i < count($arr); $i++) {
            $hashtable[$arr[$i]] += 1;
            if ($hashtable[$arr[$i]] === 3) {
                array_push($rs, $arr[$i]);
            }
        }
        return $rs;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：二分查找

遍历第一个数组，对于其中的每个数字，使用二分查找在第二个数组和第三个数组中查找该数字，若都找到，则将该数字加入结果数组。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arraysIntersection(
        self, arr1: List[int], arr2: List[int], arr3: List[int]
    ) -> List[int]:
        ans = []
        for x in arr1:
            i = bisect_left(arr2, x)
            j = bisect_left(arr3, x)
            if i < len(arr2) and j < len(arr3) and arr2[i] == x and arr3[j] == x:
                ans.append(x)
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        for (int x : arr1) {
            int i = Arrays.binarySearch(arr2, x);
            int j = Arrays.binarySearch(arr3, x);
            if (i >= 0 && j >= 0) {
                ans.add(x);
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
    vector<int> arraysIntersection(vector<int>& arr1, vector<int>& arr2, vector<int>& arr3) {
        vector<int> ans;
        for (int x : arr1) {
            auto i = lower_bound(arr2.begin(), arr2.end(), x);
            auto j = lower_bound(arr3.begin(), arr3.end(), x);
            if (*i == x && *j == x) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func arraysIntersection(arr1 []int, arr2 []int, arr3 []int) (ans []int) {
	for _, x := range arr1 {
		i := sort.SearchInts(arr2, x)
		j := sort.SearchInts(arr3, x)
		if i < len(arr2) && j < len(arr3) && arr2[i] == x && arr3[j] == x {
			ans = append(ans, x)
		}
	}
	return
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

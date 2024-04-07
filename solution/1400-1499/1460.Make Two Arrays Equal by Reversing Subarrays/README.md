# [1460. 通过翻转子数组使两个数组相等](https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-subarrays)

[English Version](/solution/1400-1499/1460.Make%20Two%20Arrays%20Equal%20by%20Reversing%20Subarrays/README_EN.md)

<!-- tags:数组,哈希表,排序 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个长度相同的整数数组&nbsp;<code>target</code>&nbsp;和&nbsp;<code>arr</code>&nbsp;。每一步中，你可以选择&nbsp;<code>arr</code>&nbsp;的任意 <strong>非空子数组</strong>&nbsp;并将它翻转。你可以执行此过程任意次。</p>

<p><em>如果你能让 <code>arr</code>&nbsp;变得与 <code>target</code>&nbsp;相同，返回 True；否则，返回 False 。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = [1,2,3,4], arr = [2,4,1,3]
<strong>输出：</strong>true
<strong>解释：</strong>你可以按照如下步骤使 arr 变成 target：
1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = [7], arr = [7]
<strong>输出：</strong>true
<strong>解释：</strong>arr 不需要做任何翻转已经与 target 相等。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = [3,7,9], arr = [3,7,11]
<strong>输出：</strong>false
<strong>解释：</strong>arr 没有数字 9 ，所以无论如何也无法变成 target 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>target.length == arr.length</code></li>
	<li><code>1 &lt;= target.length &lt;= 1000</code></li>
	<li><code>1 &lt;= target[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：排序

如果两个数组排序后相等，那么它们可以通过翻转子数组变成相等的数组。

因此，我们只需要对两个数组进行排序，然后判断排序后的数组是否相等即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $arr$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        return sorted(target) == sorted(arr)
```

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
```

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        sort(target.begin(), target.end());
        sort(arr.begin(), arr.end());
        return target == arr;
    }
};
```

```go
func canBeEqual(target []int, arr []int) bool {
	sort.Ints(target)
	sort.Ints(arr)
	return reflect.DeepEqual(target, arr)
}
```

```ts
function canBeEqual(target: number[], arr: number[]): boolean {
    target.sort((a, b) => a - b);
    arr.sort((a, b) => a - b);
    return target.join() === arr.join();
}
```

```rust
impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        target.sort();
        arr.sort();
        target == arr
    }
}
```

```php
class Solution {
    /**
     * @param Integer[] $target
     * @param Integer[] $arr
     * @return Boolean
     */
    function canBeEqual($target, $arr) {
        sort($target);
        sort($arr);
        return $target === $arr;
    }
}
```

```c
int compare(const void* a, const void* b) {
    return (*(int*) a - *(int*) b);
}

bool canBeEqual(int* target, int targetSize, int* arr, int arrSize) {
    qsort(target, targetSize, sizeof(int), compare);
    qsort(arr, arrSize, sizeof(int), compare);
    for (int i = 0; i < targetSize; ++i) {
        if (target[i] != arr[i]) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

### 方法二：计数

我们注意到，题目中给出的数组元素的范围是 $1 \sim 1000$，因此我们可以使用两个长度为 $1001$ 的数组 `cnt1` 和 `cnt2` 分别记录数组 `target` 和 `arr` 中每个元素出现的次数。最后判断两个数组是否相等即可。

我们也可以只用一个数组 `cnt`，遍历数组 `target` 和 `arr`，对于 `target[i]`，我们将 `cnt[target[i]]` 加一，对于 `arr[i]`，我们将 `cnt[arr[i]]` 减一。最后判断数组 `cnt` 中的所有元素是否都为 $0$。

时间复杂度 $O(n + M)$，空间复杂度 $O(M)$。其中 $n$ 是数组 $arr$ 的长度，而 $M$ 是数组元素的范围，本题中 $M = 1001$。

<!-- tabs:start -->

```python
class Solution:
    def canBeEqual(self, target: List[int], arr: List[int]) -> bool:
        return Counter(target) == Counter(arr)
```

```java
class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt1 = new int[1001];
        int[] cnt2 = new int[1001];
        for (int v : target) {
            ++cnt1[v];
        }
        for (int v : arr) {
            ++cnt2[v];
        }
        return Arrays.equals(cnt1, cnt2);
    }
}
```

```cpp
class Solution {
public:
    bool canBeEqual(vector<int>& target, vector<int>& arr) {
        vector<int> cnt1(1001);
        vector<int> cnt2(1001);
        for (int& v : target) {
            ++cnt1[v];
        }
        for (int& v : arr) {
            ++cnt2[v];
        }
        return cnt1 == cnt2;
    }
};
```

```go
func canBeEqual(target []int, arr []int) bool {
	cnt1 := make([]int, 1001)
	cnt2 := make([]int, 1001)
	for _, v := range target {
		cnt1[v]++
	}
	for _, v := range arr {
		cnt2[v]++
	}
	return reflect.DeepEqual(cnt1, cnt2)
}
```

```ts
function canBeEqual(target: number[], arr: number[]): boolean {
    const n = target.length;
    const cnt = Array(1001).fill(0);
    for (let i = 0; i < n; i++) {
        cnt[target[i]]++;
        cnt[arr[i]]--;
    }
    return cnt.every(v => !v);
}
```

```rust
impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        let n = target.len();
        let mut cnt = [0; 1001];
        for i in 0..n {
            cnt[target[i] as usize] += 1;
            cnt[arr[i] as usize] -= 1;
        }
        cnt.iter().all(|v| *v == 0)
    }
}
```

<!-- tabs:end -->

<!-- end -->

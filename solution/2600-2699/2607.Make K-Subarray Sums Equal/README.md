# [2607. 使子数组元素和相等](https://leetcode.cn/problems/make-k-subarray-sums-equal)

[English Version](/solution/2600-2699/2607.Make%20K-Subarray%20Sums%20Equal/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>arr</code> 和一个整数 <code>k</code> 。数组 <code>arr</code> 是一个循环数组。换句话说，数组中的最后一个元素的下一个元素是数组中的第一个元素，数组中第一个元素的前一个元素是数组中的最后一个元素。</p>

<p>你可以执行下述运算任意次：</p>

<ul>
	<li>选中 <code>arr</code> 中任意一个元素，并使其值加上 <code>1</code> 或减去 <code>1</code> 。</li>
</ul>

<p>执行运算使每个长度为 <code>k</code> 的 <strong>子数组</strong> 的元素总和都相等，返回所需要的最少运算次数。</p>

<p><strong>子数组</strong> 是数组的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,4,1,3], k = 2
<strong>输出：</strong>1
<strong>解释：</strong>在下标为 1 的元素那里执行一次运算，使其等于 3 。
执行运算后，数组变为 [1,3,1,3] 。
- 0 处起始的子数组为 [1, 3] ，元素总和为 4 
- 1 处起始的子数组为 [3, 1] ，元素总和为 4 
- 2 处起始的子数组为 [1, 3] ，元素总和为 4 
- 3 处起始的子数组为 [3, 1] ，元素总和为 4 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [2,5,5,7], k = 3
<strong>输出：</strong>5
<strong>解释：</strong>在下标为 0 的元素那里执行三次运算，使其等于 5 。在下标为 3 的元素那里执行两次运算，使其等于 5 。
执行运算后，数组变为 [5,5,5,5] 。
- 0 处起始的子数组为 [5, 5, 5] ，元素总和为 15
- 1 处起始的子数组为 [5, 5, 5] ，元素总和为 15
- 2 处起始的子数组为 [5, 5, 5] ，元素总和为 15
- 3 处起始的子数组为 [5, 5, 5] ，元素总和为 15
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学（裴蜀定理） + 中位数贪心**

题目要求每个长度为 $k$ 的子数组的元素总和相等，那么有以下等式成立：

$$
arr_i + arr_{i + 1} + \cdots + arr_{i + k - 1} = arr_{i + 1} + arr_{i + 2} + \cdots + arr_{i + k}
$$

化简得：

$$
arr_i = arr_{i + k}
$$

也即是说，数组 $arr$ 有一个大小为 $k$ 的循环节，而由于数组 $arr$ 是一个循环数组，那么数组 $arr$ 也有一个长度为 $n$ 的循环节。换句话说，数组 $arr$ 上间隔为 $k$，以及间隔为 $n$ 的元素均相等。即有：

$$
arr_i = arr_{i + k \times x + n \times y}
$$

根据裴蜀定理，有 $a \times x + b \times y = gcd(a, b)$，因此，有：

$$
arr_i = arr_{i + k \times x + n \times y} = arr_{i + gcd(k, n)}
$$

因此，数组 $arr$ 上的元素可以分为 $gcd(k, n)$ 组，每组的元素间隔为 $gcd(k, n)$，且每一组中的所有元素均相等。对于每一组，我们可以将其元素按照大小排序，然后取中位数，即可将该组中的所有元素变为中位数。对于所有组，我们将其中位数之差的绝对值求和，即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $arr$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeSubKSumEqual(self, arr: List[int], k: int) -> int:
        n = len(arr)
        g = gcd(n, k)
        ans = 0
        for i in range(g):
            t = sorted(arr[i:n:g])
            mid = t[len(t) >> 1]
            ans += sum(abs(x - mid) for x in t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length;
        int g = gcd(n, k);
        long ans = 0;
        for (int i = 0; i < g; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = i; j < n; j += g) {
                t.add(arr[j]);
            }
            t.sort((a, b) -> a - b);
            int mid = t.get(t.size() >> 1);
            for (int x : t) {
                ans += Math.abs(x - mid);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long makeSubKSumEqual(vector<int>& arr, int k) {
        int n = arr.size();
        int g = gcd(n, k);
        long long ans = 0;
        for (int i = 0; i < g; ++i) {
            vector<int> t;
            for (int j = i; j < n; j += g) {
                t.push_back(arr[j]);
            }
            sort(t.begin(), t.end());
            int mid = t[t.size() / 2];
            for (int x : t) {
                ans += abs(x - mid);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func makeSubKSumEqual(arr []int, k int) (ans int64) {
	n := len(arr)
	g := gcd(n, k)
	for i := 0; i < g; i++ {
		t := []int{}
		for j := i; j < n; j += g {
			t = append(t, arr[j])
		}
		sort.Ints(t)
		mid := t[len(t)/2]
		for _, x := range t {
			ans += int64(abs(x - mid))
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts
function makeSubKSumEqual(arr: number[], k: number): number {
    const n = arr.length;
    const g = gcd(n, k);
    let ans = 0;
    for (let i = 0; i < g; ++i) {
        const t: number[] = [];
        for (let j = i; j < n; j += g) {
            t.push(arr[j]);
        }
        t.sort((a, b) => a - b);
        const mid = t[t.length >> 1];
        for (const x of t) {
            ans += Math.abs(x - mid);
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}
```

### **...**

```

```

<!-- tabs:end -->

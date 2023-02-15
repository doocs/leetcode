# [2562. 找出数组的串联值](https://leetcode.cn/problems/find-the-array-concatenation-value)

[English Version](/solution/2500-2599/2562.Find%20the%20Array%20Concatenation%20Value/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组&nbsp;<code>nums</code> 。</p>

<p>现定义两个数字的 <strong>串联</strong>&nbsp;是由这两个数值串联起来形成的新数字。</p>

<ul>
	<li>例如，<code>15</code><span style="">&nbsp;和&nbsp;</span><code>49</code>&nbsp;的串联是&nbsp;<code>1549</code> 。</li>
</ul>

<p><code>nums</code>&nbsp;的 <strong>串联值</strong>&nbsp;最初等于 <code>0</code> 。执行下述操作直到&nbsp;<code>nums</code>&nbsp;变为空：</p>

<ul>
	<li>如果&nbsp;<code>nums</code>&nbsp;中存在不止一个数字，分别选中 <code>nums</code> 中的第一个元素和最后一个元素，将二者串联得到的值加到&nbsp;<code>nums</code>&nbsp;的 <strong>串联值</strong> 上，然后从&nbsp;<code>nums</code>&nbsp;中删除第一个和最后一个元素。</li>
	<li>如果仅存在一个元素，则将该元素的值加到&nbsp;<code>nums</code> 的串联值上，然后删除这个元素。</li>
</ul>

<p>返回执行完所有操作后<em>&nbsp;</em><code>nums</code> 的串联值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [7,52,2,4]
<b>输出：</b>596
<b>解释：</b>在执行任一步操作前，nums 为 [7,52,2,4] ，串联值为 0 。
 - 在第一步操作中：
我们选中第一个元素 7 和最后一个元素 4 。
二者的串联是 74 ，将其加到串联值上，所以串联值等于 74 。
接着我们从 nums 中移除这两个元素，所以 nums 变为 [52,2] 。
 - 在第二步操作中： 
我们选中第一个元素 52 和最后一个元素 2 。 
二者的串联是 522 ，将其加到串联值上，所以串联值等于 596 。
接着我们从 nums 中移除这两个元素，所以 nums 变为空。
由于串联值等于 596 ，所以答案就是 596 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,14,13,8,12]
<b>输出：</b>673
<b>解释：</b>在执行任一步操作前，nums 为 [5,14,13,8,12] ，串联值为 0 。 
- 在第一步操作中： 
我们选中第一个元素 5 和最后一个元素 12 。 
二者的串联是 512 ，将其加到串联值上，所以串联值等于 512 。 
接着我们从 nums 中移除这两个元素，所以 nums 变为 [14,13,8] 。
- 在第二步操作中：
我们选中第一个元素 14 和最后一个元素 8 。
二者的串联是 148 ，将其加到串联值上，所以串联值等于 660 。
接着我们从 nums 中移除这两个元素，所以 nums 变为 [13] 。 
- 在第三步操作中：
nums 只有一个元素，所以我们选中 13 并将其加到串联值上，所以串联值等于 673 。
接着我们从 nums 中移除这个元素，所以 nums 变为空。 
由于串联值等于 673 ，所以答案就是 673 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

从数组两端开始，每次取出一个元素，将其与另一个元素拼接，然后将拼接后的结果加到答案中。重复这个过程，直到数组为空。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(n \times \log M)$。其中 $n$ 和 $M$ 分别是数组的长度和数组中的最大值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findTheArrayConcVal(self, nums: List[int]) -> int:
        ans = 0
        i, j = 0, len(nums) - 1
        while i < j:
            ans += int(str(nums[i]) + str(nums[j]))
            i, j = i + 1, j - 1
        if i == j:
            ans += nums[i]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int i = 0, j = nums.length - 1;
        for (; i < j; ++i, --j) {
            ans += Integer.parseInt(nums[i] + "" + nums[j]);
        }
        if (i == j) {
            ans += nums[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long findTheArrayConcVal(vector<int>& nums) {
        long long ans = 0;
        int i = 0, j = nums.size() - 1;
        for (; i < j; ++i, --j) {
            ans += stoi(to_string(nums[i]) + to_string(nums[j]));
        }
        if (i == j) {
            ans += nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func findTheArrayConcVal(nums []int) (ans int64) {
	i, j := 0, len(nums)-1
	for ; i < j; i, j = i+1, j-1 {
		x, _ := strconv.Atoi(strconv.Itoa(nums[i]) + strconv.Itoa(nums[j]))
		ans += int64(x)
	}
	if i == j {
		ans += int64(nums[i])
	}
	return
}
```

### **TypeScript**

```ts
function findTheArrayConcVal(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    let i = 0;
    let j = n - 1;
    while (i < j) {
        ans += Number(`${nums[i]}${nums[j]}`);
        i++;
        j--;
    }
    if (i === j) {
        ans += nums[i];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_the_array_conc_val(nums: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut ans = 0;
        let mut i = 0;
        let mut j = n - 1;
        while i < j {
            ans += format!("{}{}", nums[i], nums[j]).parse::<i64>().unwrap();
            i += 1;
            j -= 1;
        }
        if i == j {
            ans += nums[i] as i64;
        }
        ans
    }
}
```

### **C**

```c
int getLen(int num) {
    int res = 0;
    while (num) {
        num /= 10;
        res++;
    }
    return res;
}

long long findTheArrayConcVal(int *nums, int numsSize) {
    long long ans = 0;
    int i = 0;
    int j = numsSize - 1;
    while (i < j) {
        ans += nums[i] * pow(10, getLen(nums[j])) + nums[j];
        i++;
        j--;
    }
    if (i == j) {
        ans += nums[i];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

# [面试题 45. 把数组排成最小的数](https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>[10,2]</code>
<strong>输出:</strong> &quot;<code>102&quot;</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> <code>[3,30,34,5,9]</code>
<strong>输出:</strong> &quot;<code>3033459&quot;</code></pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt; nums.length &lt;= 100</code></li>
</ul>

<p><strong>说明: </strong></p>

<ul>
	<li>输出结果可能非常大，所以你需要返回一个字符串而不是整数</li>
	<li>拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：自定义排序**

将数组中的数字转换为字符串，然后按照字符串拼接的大小进行排序。具体地，比较两个字符串 $a$ 和 $b$，如果 $a + b \lt b + a$，则 $a$ 小于 $b$，否则 $a$ 大于 $b$。

时间复杂度 $O(n \times \log n + n \times m)$，空间复杂度 $O(n \times m)$。其中 $n $ 和 $m$ 分别为数组的长度和字符串的平均长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minNumber(self, nums: List[int]) -> str:
        def cmp(a, b):
            x, y = a + b, b + a
            return -1 if x < y else 1

        ans = [str(x) for x in nums]
        ans.sort(key=cmp_to_key(cmp))
        return "".join(ans)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String minNumber(int[] nums) {
        return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (a + b).compareTo(b + a))
            .reduce((a, b) -> a + b)
            .orElse("");
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string minNumber(vector<int>& nums) {
        vector<string> arr;
        for (int& x : nums) {
            arr.emplace_back(to_string(x));
        }
        sort(arr.begin(), arr.end(), [](const auto& a, const auto& b) {
            return a + b < b + a;
        });
        string ans;
        for (auto& x : arr) {
            ans += x;
        }
        return ans;
    }
};
```

### **Go**

```go
func minNumber(nums []int) string {
	arr := []string{}
	for _, x := range nums {
		arr = append(arr, strconv.Itoa(x))
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i]+arr[j] < arr[j]+arr[i] })
	return strings.Join(arr, "")
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {string}
 */
var minNumber = function (nums) {
    nums.sort((a, b) => {
        const x = a + '' + b;
        const y = b + '' + a;
        return x < y ? -1 : 1;
    });
    return nums.join('');
};
```

### **TypeScript**

```ts
function minNumber(nums: number[]): string {
    return nums
        .sort((a, b) => Number(`${a}${b}`) - Number(`${b}${a}`))
        .join('');
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_number(mut nums: Vec<i32>) -> String {
        nums.sort_by(|a, b| format!("{}{}", a, b).cmp(&format!("{}{}", b, a)));
        nums.iter().map(|num| num.to_string()).collect()
    }
}
```

### **C#**

```cs
public class Solution {
    public string MinNumber(int[] nums) {
        List<string> ans = new List<string>();
        foreach (int x in nums) {
            ans.Add(x.ToString());
        }
        ans.Sort((a, b) => (a + b).CompareTo(b + a));
        return string.Join("", ans);
    }
}
```

### **...**

```

```

<!-- tabs:end -->

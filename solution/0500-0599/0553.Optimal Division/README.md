# [553. 最优除法](https://leetcode.cn/problems/optimal-division)

[English Version](/solution/0500-0599/0553.Optimal%20Division/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一正整数数组<strong> </strong><code>nums</code><strong>，</strong><code>nums</code> 中的相邻整数将进行浮点除法。例如，&nbsp;[2,3,4] -&gt; 2 / 3 / 4 。</p>

<ul>
	<li>例如，<code>nums = [2,3,4]</code>，我们将求表达式的值&nbsp;<code>"2/3/4"</code>。</li>
</ul>

<p>但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，以便计算后的表达式的值为最大值。</p>

<p>以字符串格式返回具有最大值的对应表达式。</p>

<p><strong>注意：</strong>你的表达式不应该包含多余的括号。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> [1000,100,10,2]
<strong>输出:</strong> "1000/(100/10/2)"
<strong>解释: </strong>1000/(100/10/2) = 1000/((100/10)/2) = 200
但是，以下加粗的括号 "1000/(<strong>(</strong>100/10<strong>)</strong>/2)" 是冗余的，
因为他们并不影响操作的优先级，所以你需要返回 "1000/(100/10/2)"。

其他用例:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
</pre>

<p>&nbsp;</p>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,4]
<strong>输出:</strong> "2/(3/4)"
<strong>解释:</strong> (2/(3/4)) = 8/3 = 2.667
可以看出，在尝试了所有的可能性之后，我们无法得到一个结果大于 2.667 的表达式。
</pre>

<p>&nbsp;</p>

<p><strong>说明:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 1000</code></li>
	<li>对于给定的输入只有一种最优除法。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

贪心。

要使得除法的结果最大，分子应该尽可能大，而分母应该尽可能小。

分子最大应该是 `nums[0]`，而分母最大是 `nums[1] / nums[2] / ... / nums[n - 1]`，此时的除法结果最大。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def optimalDivision(self, nums: List[int]) -> str:
        n = len(nums)
        if n == 1:
            return str(nums[0])
        if n == 2:
            return f'{nums[0]}/{nums[1]}'
        return f'{nums[0]}/({"/".join(map(str, nums[1:]))})'
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] + "";
        }
        if (n == 2) {
            return nums[0] + "/" + nums[1];
        }
        StringBuilder ans = new StringBuilder(nums[0] + "/(");
        for (int i = 1; i < n - 1; ++i) {
            ans.append(nums[i] + "/");
        }
        ans.append(nums[n - 1] + ")");
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string optimalDivision(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return to_string(nums[0]);
        if (n == 2) return to_string(nums[0]) + "/" + to_string(nums[1]);
        string ans = to_string(nums[0]) + "/(";
        for (int i = 1; i < n - 1; i++) ans.append(to_string(nums[i]) + "/");
        ans.append(to_string(nums[n - 1]) + ")");
        return ans;
    }
};
```

### **Go**

```go
func optimalDivision(nums []int) string {
	n := len(nums)
	if n == 1 {
		return strconv.Itoa(nums[0])
	}
	if n == 2 {
		return fmt.Sprintf("%d/%d", nums[0], nums[1])
	}
	ans := &strings.Builder{}
	ans.WriteString(fmt.Sprintf("%d/(", nums[0]))
	for _, num := range nums[1 : n-1] {
		ans.WriteString(strconv.Itoa(num))
		ans.WriteByte('/')
	}
	ans.WriteString(fmt.Sprintf("%d)", nums[n-1]))
	return ans.String()
}
```

### **TypeScript**

```ts
function optimalDivision(nums: number[]): string {
    const n = nums.length;
    const res = nums.join('/');
    if (n > 2) {
        const index = res.indexOf('/') + 1;
        return `${res.slice(0, index)}(${res.slice(index)})`;
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn optimal_division(nums: Vec<i32>) -> String {
        let n = nums.len();
        match n {
            1 => nums[0].to_string(),
            2 => nums[0].to_string() + "/" + &nums[1].to_string(),
            _ => {
                let mut res = nums[0].to_string();
                res.push_str("/(");
                for i in 1..n {
                    res.push_str(&nums[i].to_string());
                    res.push('/');
                }
                res.pop();
                res.push(')');
                res
            }
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->

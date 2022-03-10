# [553. Optimal Division](https://leetcode.com/problems/optimal-division)

[中文文档](/solution/0500-0599/0553.Optimal%20Division/README.md)

## Description

<p>You are given an integer array <code>nums</code>. The adjacent integers in <code>nums</code> will perform the float division.</p>

<ul>
	<li>For example, for <code>nums = [2,3,4]</code>, we will evaluate the expression <code>&quot;2/3/4&quot;</code>.</li>
</ul>

<p>However, you can add any number of parenthesis at any position to change the priority of operations. You want to add these parentheses such the value of the expression after the evaluation is maximum.</p>

<p>Return <em>the corresponding expression that has the maximum value in string format</em>.</p>

<p><strong>Note:</strong> your expression should not contain redundant parenthesis.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1000,100,10,2]
<strong>Output:</strong> &quot;1000/(100/10/2)&quot;
<strong>Explanation:</strong>
1000/(100/10/2) = 1000/((100/10)/2) = 200
However, the bold parenthesis in &quot;1000/((100/10)/2)&quot; are redundant, since they don&#39;t influence the operation priority. So you should return &quot;1000/(100/10/2)&quot;.
Other cases:
1000/(100/10)/2 = 50
1000/(100/(10/2)) = 50
1000/100/10/2 = 0.5
1000/100/(10/2) = 2
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,4]
<strong>Output:</strong> &quot;2/(3/4)&quot;
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [2]
<strong>Output:</strong> &quot;2&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10</code></li>
	<li><code>2 &lt;= nums[i] &lt;= 1000</code></li>
	<li>There is only one optimal division for the given iput.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

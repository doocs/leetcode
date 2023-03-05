# [1073. Adding Two Negabinary Numbers](https://leetcode.com/problems/adding-two-negabinary-numbers)

[中文文档](/solution/1000-1099/1073.Adding%20Two%20Negabinary%20Numbers/README.md)

## Description

<p>Given two numbers <code>arr1</code> and <code>arr2</code> in base <strong>-2</strong>, return the result of adding them together.</p>

<p>Each number is given in <em>array format</em>:&nbsp; as an array of 0s and 1s, from most significant bit to least significant bit.&nbsp; For example, <code>arr = [1,1,0,1]</code> represents the number <code>(-2)^3&nbsp;+ (-2)^2 + (-2)^0 = -3</code>.&nbsp; A number <code>arr</code> in <em>array, format</em> is also guaranteed to have no leading zeros: either&nbsp;<code>arr == [0]</code> or <code>arr[0] == 1</code>.</p>

<p>Return the result of adding <code>arr1</code> and <code>arr2</code> in the same format: as an array of 0s and 1s with no leading zeros.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [1,1,1,1,1], arr2 = [1,0,1]
<strong>Output:</strong> [1,0,0,0,0]
<strong>Explanation: </strong>arr1 represents 11, arr2 represents 5, the output represents 16.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [0], arr2 = [0]
<strong>Output:</strong> [0]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr1 = [0], arr2 = [1]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length,&nbsp;arr2.length &lt;= 1000</code></li>
	<li><code>arr1[i]</code>&nbsp;and <code>arr2[i]</code> are&nbsp;<code>0</code> or <code>1</code></li>
	<li><code>arr1</code> and <code>arr2</code> have no leading zeros</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addNegabinary(self, arr1: List[int], arr2: List[int]) -> List[int]:
        i, j = len(arr1) - 1, len(arr2) - 1
        c = 0
        ans = []
        while i >= 0 or j >= 0 or c:
            a = 0 if i < 0 else arr1[i]
            b = 0 if j < 0 else arr2[j]
            x = a + b + c
            c = 0
            if x > 1:
                x -= 2
                c -= 1
            if x < 0:
                x += 2
                c += 1
            ans.append(x)
            i, j = i - 1, j - 1
        while len(ans) > 1 and ans[-1] == 0:
            ans.pop()
        return ans[::-1]
```

### **Java**

```java
class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1, j = arr2.length - 1;
        List<Integer> ans = new ArrayList<>();
        for (int c = 0; i >= 0 || j >= 0 || c != 0; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x > 1) {
                x -= 2;
                c -= 1;
            }
            if (x < 0) {
                x += 2;
                c += 1;
            }
            ans.add(x);
        }
        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) {
            ans.remove(ans.size() - 1);
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(x -> x).toArray();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> addNegabinary(vector<int>& arr1, vector<int>& arr2) {
        int i = arr1.size() - 1, j = arr2.size() - 1;
        vector<int> ans;
        for (int c = 0; i >= 0 || j >= 0 || c; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x > 1) {
                x -= 2;
                c -= 1;
            }
            if (x < 0) {
                x += 2;
                c += 1;
            }
            ans.push_back(x);
        }
        while (ans.size() > 1 && ans.back() == 0) {
            ans.pop_back();
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

### **Go**

```go
func addNegabinary(arr1 []int, arr2 []int) (ans []int) {
	i, j := len(arr1)-1, len(arr2)-1
	for c := 0; i >= 0 || j >= 0 || c != 0; i, j = i-1, j-1 {
		x := c
		if i >= 0 {
			x += arr1[i]
		}
		if j >= 0 {
			x += arr2[j]
		}
		c = 0
		if x > 1 {
			x -= 2
			c -= 1
		}
		if x < 0 {
			x += 2
			c += 1
		}
		ans = append(ans, x)
	}
	for len(ans) > 1 && ans[len(ans)-1] == 0 {
		ans = ans[:len(ans)-1]
	}
	for i, j = 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return ans
}
```

### **TypeScript**

```ts
function addNegabinary(arr1: number[], arr2: number[]): number[] {
    let i = arr1.length - 1,
        j = arr2.length - 1;
    const ans: number[] = [];
    for (let c = 0; i >= 0 || j >= 0 || c; --i, --j) {
        const a = i < 0 ? 0 : arr1[i];
        const b = j < 0 ? 0 : arr2[j];
        let x = a + b + c;
        c = 0;
        if (x > 1) {
            x -= 2;
            c -= 1;
        }
        if (x < 0) {
            x += 2;
            c += 1;
        }
        ans.push(x);
    }
    while (ans.length > 1 && ans[ans.length - 1] === 0) {
        ans.pop();
    }
    return ans.reverse();
}
```

### **...**

```

```

<!-- tabs:end -->

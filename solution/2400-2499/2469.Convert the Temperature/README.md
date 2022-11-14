# [2469. 温度转换](https://leetcode.cn/problems/convert-the-temperature)

[English Version](/solution/2400-2499/2469.Convert%20the%20Temperature/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个四舍五入到两位小数的非负浮点数 <code>celsius</code> 来表示温度，以 <strong>摄氏度</strong>（<strong>Celsius</strong>）为单位。</p>

<p>你需要将摄氏度转换为 <strong>开氏度</strong>（<strong>Kelvin</strong>）和 <strong>华氏度</strong>（<strong>Fahrenheit</strong>），并以数组 <code>ans = [kelvin, fahrenheit]</code> 的形式返回结果。</p>

<p>返回数组<em> <code>ans</code></em> 。与实际答案误差不超过 <code>10<sup>-5</sup></code> 的会视为正确答案<strong>。</strong></p>

<p><strong>注意：</strong></p>

<ul>
	<li><code>开氏度 = 摄氏度 + 273.15</code></li>
	<li><code>华氏度 = 摄氏度 * 1.80 + 32.00</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1 ：</strong></p>

<pre><strong>输入：</strong>celsius = 36.50
<strong>输出：</strong>[309.65000,97.70000]
<strong>解释：</strong>36.50 摄氏度：转换为开氏度是 309.65 ，转换为华氏度是 97.70 。</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>celsius = 122.11
<strong>输出：</strong>[395.26000,251.79800]
<strong>解释：</strong>122.11 摄氏度：转换为开氏度是 395.26 ，转换为华氏度是 251.798 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= celsius &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

直接根据题意模拟即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def convertTemperature(self, celsius: float) -> List[float]:
        return [celsius + 273.15, celsius * 1.8 + 32]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double[] convertTemperature(double celsius) {
        return new double[] {celsius + 273.15, celsius * 1.8 + 32};
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<double> convertTemperature(double celsius) {
        return {celsius + 273.15, celsius * 1.8 + 32};
    }
};
```

### **Go**

```go
func convertTemperature(celsius float64) []float64 {
	return []float64{celsius + 273.15, celsius*1.8 + 32}
}
```

### **TypeScript**

```ts
function convertTemperature(celsius: number): number[] {
    return [celsius + 273.15, celsius * 1.8 + 32];
}
```

### **Rust**

```rust
impl Solution {
    pub fn convert_temperature(celsius: f64) -> Vec<f64> {
        vec![celsius + 273.15, celsius * 1.8 + 32.0]
    }
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
double *convertTemperature(double celsius, int *returnSize) {
    double *ans = malloc(sizeof(double) * 2);
    ans[0] = celsius + 273.15;
    ans[1] = celsius * 1.8 + 32;
    *returnSize = 2;
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->

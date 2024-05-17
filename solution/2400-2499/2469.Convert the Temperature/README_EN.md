---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2469.Convert%20the%20Temperature/README_EN.md
rating: 1153
source: Weekly Contest 319 Q1
tags:
    - Math
---

<!-- problem:start -->

# [2469. Convert the Temperature](https://leetcode.com/problems/convert-the-temperature)

[中文文档](/solution/2400-2499/2469.Convert%20the%20Temperature/README.md)

## Description

<!-- description:start -->

<p>You are given a non-negative floating point number rounded to two decimal places <code>celsius</code>, that denotes the <strong>temperature in Celsius</strong>.</p>

<p>You should convert Celsius into <strong>Kelvin</strong> and <strong>Fahrenheit</strong> and return it as an array <code>ans = [kelvin, fahrenheit]</code>.</p>

<p>Return <em>the array <code>ans</code>. </em>Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p><strong>Note that:</strong></p>

<ul>
	<li><code>Kelvin = Celsius + 273.15</code></li>
	<li><code>Fahrenheit = Celsius * 1.80 + 32.00</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> celsius = 36.50
<strong>Output:</strong> [309.65000,97.70000]
<strong>Explanation:</strong> Temperature at 36.50 Celsius converted in Kelvin is 309.65 and converted in Fahrenheit is 97.70.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> celsius = 122.11
<strong>Output:</strong> [395.26000,251.79800]
<strong>Explanation:</strong> Temperature at 122.11 Celsius converted in Kelvin is 395.26 and converted in Fahrenheit is 251.798.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= celsius &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can directly simulate according to the problem description.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def convertTemperature(self, celsius: float) -> List[float]:
        return [celsius + 273.15, celsius * 1.8 + 32]
```

#### Java

```java
class Solution {
    public double[] convertTemperature(double celsius) {
        return new double[] {celsius + 273.15, celsius * 1.8 + 32};
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<double> convertTemperature(double celsius) {
        return {celsius + 273.15, celsius * 1.8 + 32};
    }
};
```

#### Go

```go
func convertTemperature(celsius float64) []float64 {
	return []float64{celsius + 273.15, celsius*1.8 + 32}
}
```

#### TypeScript

```ts
function convertTemperature(celsius: number): number[] {
    return [celsius + 273.15, celsius * 1.8 + 32];
}
```

#### Rust

```rust
impl Solution {
    pub fn convert_temperature(celsius: f64) -> Vec<f64> {
        vec![celsius + 273.15, celsius * 1.8 + 32.0]
    }
}
```

#### C

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
double* convertTemperature(double celsius, int* returnSize) {
    double* ans = malloc(sizeof(double) * 2);
    ans[0] = celsius + 273.15;
    ans[1] = celsius * 1.8 + 32;
    *returnSize = 2;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->

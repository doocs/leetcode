# [1491. Average Salary Excluding the Minimum and Maximum Salary](https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary)

[中文文档](/solution/1400-1499/1491.Average%20Salary%20Excluding%20the%20Minimum%20and%20Maximum%20Salary/README.md)

## Description

<p>You are given an array of <strong>unique</strong> integers <code>salary</code> where <code>salary[i]</code> is the salary of the <code>i<sup>th</sup></code> employee.</p>

<p>Return <em>the average salary of employees excluding the minimum and maximum salary</em>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> salary = [4000,3000,1000,2000]
<strong>Output:</strong> 2500.00000
<strong>Explanation:</strong> Minimum salary and maximum salary are 1000 and 4000 respectively.
Average salary excluding minimum and maximum salary is (2000+3000) / 2 = 2500
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> salary = [1000,2000,3000]
<strong>Output:</strong> 2000.00000
<strong>Explanation:</strong> Minimum salary and maximum salary are 1000 and 3000 respectively.
Average salary excluding minimum and maximum salary is (2000) / 1 = 2000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= salary.length &lt;= 100</code></li>
	<li><code>1000 &lt;= salary[i] &lt;= 10<sup>6</sup></code></li>
	<li>All the integers of <code>salary</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **Rust**

```rust
impl Solution {
    pub fn average(salary: Vec<i32>) -> f64 {
        let n = salary.len() as i32;
        let mut min = i32::MAX;
        let mut max = i32::MIN;
        let mut sum = 0;
        for &num in salary.iter() {
            min = min.min(num);
            max = max.max(num);
            sum += num;
        }
        f64::from(sum - min - max) / f64::from(n - 2)
    }
}
```

### **...**

```

```

<!-- tabs:end -->

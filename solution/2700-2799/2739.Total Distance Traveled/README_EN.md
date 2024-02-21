# [2739. Total Distance Traveled](https://leetcode.com/problems/total-distance-traveled)

[中文文档](/solution/2700-2799/2739.Total%20Distance%20Traveled/README.md)

<!-- tags:Math,Simulation -->

## Description

<p>A truck has two fuel tanks. You are given two integers, <code>mainTank</code> representing the fuel present in the main tank in liters and <code>additionalTank</code> representing the fuel present in the additional tank in liters.</p>

<p>The truck has a mileage of <code>10</code> km per liter. Whenever <code>5</code> liters of fuel get&nbsp;used up in the main tank,&nbsp;if the additional tank has at least <code>1</code> liters of fuel, <code>1</code> liters of fuel will be transferred from the additional tank to the main tank.</p>

<p>Return <em>the maximum distance which can be traveled.</em></p>

<p><strong>Note: </strong>Injection from the additional tank is not continuous. It happens suddenly and immediately for every 5 liters consumed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> mainTank = 5, additionalTank = 10
<strong>Output:</strong> 60
<strong>Explanation:</strong> 
After spending 5 litre of fuel, fuel remaining is (5 - 5 + 1) = 1 litre and distance traveled is 50km.
After spending another 1 litre of fuel, no fuel gets injected in the main tank and the main tank becomes empty.
Total distance traveled is 60km.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> mainTank = 1, additionalTank = 2
<strong>Output:</strong> 10
<strong>Explanation:</strong> 
After spending 1 litre of fuel, the main tank becomes empty.
Total distance traveled is 10km.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= mainTank, additionalTank &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def distanceTraveled(self, mainTank: int, additionalTank: int) -> int:
        ans = cur = 0
        while mainTank:
            cur += 1
            ans += 10
            mainTank -= 1
            if cur % 5 == 0 and additionalTank:
                additionalTank -= 1
                mainTank += 1
        return ans
```

```java
class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0, cur = 0;
        while (mainTank > 0) {
            cur++;
            ans += 10;
            mainTank--;
            if (cur % 5 == 0 && additionalTank > 0) {
                additionalTank--;
                mainTank++;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int distanceTraveled(int mainTank, int additionalTank) {
        int ans = 0, cur = 0;
        while (mainTank > 0) {
            cur++;
            ans += 10;
            mainTank--;
            if (cur % 5 == 0 && additionalTank > 0) {
                additionalTank--;
                mainTank++;
            }
        }
        return ans;
    }
};
```

```go
func distanceTraveled(mainTank int, additionalTank int) (ans int) {
	cur := 0
	for mainTank > 0 {
		cur++
		ans += 10
		mainTank--
		if cur%5 == 0 && additionalTank > 0 {
			additionalTank--
			mainTank++
		}
	}
	return
}
```

```rust
impl Solution {
    pub fn distance_traveled(mut main_tank: i32, mut additional_tank: i32) -> i32 {
        let mut cur = 0;
        let mut ans = 0;

        while main_tank > 0 {
            cur += 1;
            main_tank -= 1;
            ans += 10;

            if cur % 5 == 0 && additional_tank > 0 {
                additional_tank -= 1;
                main_tank += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->

# [860. Lemonade Change](https://leetcode.com/problems/lemonade-change)

[中文文档](/solution/0800-0899/0860.Lemonade%20Change/README.md)

## Description

<p>At a lemonade stand, each lemonade costs <code>$5</code>. Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills). Each customer will only buy one lemonade and pay with either a <code>$5</code>, <code>$10</code>, or <code>$20</code> bill. You must provide the correct change to each customer so that the net transaction is that the customer pays <code>$5</code>.</p>

<p>Note that you do not have any change in hand at first.</p>

<p>Given an integer array <code>bills</code> where <code>bills[i]</code> is the bill the <code>i<sup>th</sup></code> customer pays, return <code>true</code> <em>if you can provide every customer with the correct change, or</em> <code>false</code> <em>otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> bills = [5,5,5,10,20]
<strong>Output:</strong> true
<strong>Explanation:</strong> 
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> bills = [5,5,10,10,20]
<strong>Output:</strong> false
<strong>Explanation:</strong> 
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we can not give the change of $15 back because we only have two $10 bills.
Since not every customer received the correct change, the answer is false.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bills.length &lt;= 10<sup>5</sup></code></li>
	<li><code>bills[i]</code> is either <code>5</code>, <code>10</code>, or <code>20</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lemonadeChange(self, bills: List[int]) -> bool:
        five = ten = 0
        for v in bills:
            if v == 5:
                five += 1
            elif v == 10:
                ten += 1
                five -= 1
            else:
                if ten:
                    ten -= 1
                    five -= 1
                else:
                    five -= 3
            if five < 0:
                return False
        return True
```

### **Java**

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int v : bills) {
            if (v == 5) {
                ++five;
            } else if (v == 10) {
                ++ten;
                --five;
            } else {
                if (ten > 0) {
                    --ten;
                    --five;
                } else {
                    five -= 3;
                }
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool lemonadeChange(vector<int>& bills) {
        int five = 0, ten = 0;
        for (int v : bills) {
            if (v == 5)
                ++five;
            else if (v == 10) {
                ++ten;
                --five;
            } else {
                if (ten)
                    --ten, --five;
                else
                    five -= 3;
            }
            if (five < 0) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func lemonadeChange(bills []int) bool {
	five, ten := 0, 0
	for _, v := range bills {
		if v == 5 {
			five++
		} else if v == 10 {
			ten++
			five--
		} else {
			if ten > 0 {
				ten--
				five--
			} else {
				five -= 3
			}
		}
		if five < 0 {
			return false
		}
	}
	return true
}
```

### **TypeScript**

```ts
function lemonadeChange(bills: number[]): boolean {
    let five = 0;
    let ten = 0;
    for (let bill of bills) {
        switch (bill) {
            case 5:
                five++;
                break;
            case 10:
                five--;
                ten++;
                break;
            case 20:
                if (ten !== 0) {
                    ten -= 1;
                    bill -= 10;
                }
                five -= bill / 5 - 1;
                break;
        }

        if (five < 0) {
            return false;
        }
    }
    return true;
}
```

### **Rust**

```rust
impl Solution {
    pub fn lemonade_change(bills: Vec<i32>) -> bool {
        let (mut five, mut ten) = (0, 0);
        for bill in bills.iter() {
            match bill {
                5 => {
                    five += 1;
                }
                10 => {
                    five -= 1;
                    ten += 1;
                }
                _ => {
                    if ten != 0 {
                        ten -= 1;
                        five -= 1;
                    } else {
                        five -= 3;
                    }
                }
            }

            if five < 0 {
                return false;
            }
        }
        true
    }
}
```

### **...**

```

```

<!-- tabs:end -->

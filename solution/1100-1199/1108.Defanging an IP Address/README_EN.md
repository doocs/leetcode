# [1108. Defanging an IP Address](https://leetcode.com/problems/defanging-an-ip-address)

[中文文档](/solution/1100-1199/1108.Defanging%20an%20IP%20Address/README.md)

## Description

<p>Given a valid (IPv4) IP <code>address</code>, return a defanged version of that IP address.</p>

<p>A <em>defanged&nbsp;IP address</em>&nbsp;replaces every period <code>&quot;.&quot;</code> with <code>&quot;[.]&quot;</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> address = "1.1.1.1"

<strong>Output:</strong> "1[.]1[.]1[.]1"

</pre><p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> address = "255.100.50.0"

<strong>Output:</strong> "255[.]100[.]50[.]0"

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li>The given <code>address</code> is a valid IPv4 address.</li>

</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def defangIPaddr(self, address: str) -> str:
        return address.replace('.', '[.]')
```

### **Java**

```java
class Solution {
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }
}
```

### **TypeScript**

```ts
function defangIPaddr(address: string): string {
    return address.split('.').join('[.]');
}
```

### **C++**

```cpp
class Solution {
public:
    string defangIPaddr(string address) {
        for (int i = address.size(); i >= 0; --i) {
            if (address[i] == '.') {
                address.replace(i, 1, "[.]");
            }
        }
        return address;
    }
};
```

### **Go**

```go
func defangIPaddr(address string) string {
	return strings.Replace(address, ".", "[.]", -1)
}
```

### **...**

```

```

<!-- tabs:end -->

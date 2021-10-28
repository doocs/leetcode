# [1628. 设计带解析函数的表达式树](https://leetcode-cn.com/problems/design-an-expression-tree-with-evaluate-function)

[English Version](/solution/1600-1699/1628.Design%20an%20Expression%20Tree%20With%20Evaluate%20Function/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个算术表达式的后缀表示法的标记（token） <code>postfix</code> ，构造并返回该表达式对应的二叉表达式树。</p>

<p><b>后缀</b>表示法是一种将操作数写在运算符之前的表示法。例如，表达式 <code>4*(5-(2+7))</code> 的后缀表示法表示为数组 <code>postfix = ["4","5","7","2","+","-","*"]</code> 。</p>

<p>抽象类 <code>Node</code> 需要用于实现二叉表达式树。我们将通过 <code>evaluate</code> 函数来测试返回的树是否能够解析树中的值。你不可以移除 <code>Node</code> 类，但你可以按需修改此类，也可以定义其他类来实现它。</p>

<p><a href="https://en.wikipedia.org/wiki/Binary_expression_tree"><strong>二叉表达式树</strong></a>是一种表达算术表达式的二叉树。二叉表达式树中的每一个节点都有零个或两个子节点。 叶节点（有 0 个子节点的节点）表示操作数，非叶节点（有 2 个子节点的节点）表示运算符： <code>'+'</code> （加）、 <code>'-'</code> （减）、 <code>'*'</code> （乘）和 <code>'/'</code> （除）。</p>

<p>我们保证任何子树对应值的绝对值不超过 <code>10<sup>9</sup></code> ，且所有操作都是有效的（即没有除以零的操作）</p>

<p><b>进阶：</b> 你可以将表达式树设计得更模块化吗？例如，你的设计能够不修改现有的 <code>evaluate</code> 的实现就能支持更多的操作符吗？</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1628.Design%20an%20Expression%20Tree%20With%20Evaluate%20Function/images/untitled-diagram.png" style="width: 242px; height: 241px;"></strong></p>

<pre><b>输入：</b> s = ["3","4","+","2","*","7","/"]
<b>输出：</b> 2
<b>解释：</b> 此表达式可解析为上述二叉树，其对应表达式为 (<code>(3+4)*2)/7) = 14/7 = 2.</code>
</pre>

<p><strong>示例 2:</strong></p>

<p><strong><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1628.Design%20an%20Expression%20Tree%20With%20Evaluate%20Function/images/untitled-diagram2.png" style="width: 222px; height: 232px;"></strong></p>

<pre><strong>输入:</strong> s = ["4","5","7","2","+","-","*"]
<strong>输出:</strong> -16
<strong>解释:</strong> 此表达式可解析为上述二叉树，其对应表达式为 4*(5-<code>(2+7)) = 4*(-4) = -16.</code>
</pre>

<p><strong>示例 3:</strong></p>

<pre><strong>输入:</strong> s = ["4","2","+","3","5","1","-","*","+"]
<strong>输出:</strong> 18
</pre>

<p><strong>示例 4:</strong></p>

<pre><strong>输入:</strong> s = ["100","200","+","2","/","5","*","7","+"]
<strong>输出:</strong> 757
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt; 100</code></li>
	<li><code>s.length</code> 是奇数。</li>
	<li><code>s</code> 包含数字和字符 <code>'+'</code> 、 <code>'-'</code> 、 <code>'*'</code> 以及 <code>'/'</code> 。</li>
	<li>如果 <code>s[i]</code> 是数，则对应的整数不超过 <code>10<sup>5</sup></code> 。</li>
	<li><code>s</code> 保证是一个有效的表达式。</li>
	<li>结果值和所有过程值的绝对值均不超过 <code>10<sup>9</sup></code> 。</li>
	<li>保证表达式不包含除以零的操作。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
import abc 
from abc import ABC, abstractmethod 
"""
This is the interface for the expression tree Node.
You should not remove it, and you can define some classes to implement it.
"""

class Node(ABC):
    @abstractmethod
    # define your fields here
    def evaluate(self) -> int:
        pass

class MyNode(Node):

    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

    def evaluate(self) -> int:
        x = self.val
        if x.isdigit():
            return int(x)
        
        left, right = self.left.evaluate(), self.right.evaluate()
        if x == '+':
            return left + right
        if x == '-':
            return left - right
        if x == '*':
            return left * right
        if x == '/':
            return left // right


"""    
This is the TreeBuilder class.
You can treat it as the driver code that takes the postinfix input
and returns the expression tree represnting it as a Node.
"""

class TreeBuilder(object):
    def buildTree(self, postfix: List[str]) -> 'Node':
        stk = []
        for s in postfix:
            node = MyNode(s)
            if not s.isdigit():
                node.right = stk.pop()
                node.left = stk.pop()
            stk.append(node)
        return stk[-1]
		
"""
Your TreeBuilder object will be instantiated and called as such:
obj = TreeBuilder();
expTree = obj.buildTree(postfix);
ans = expTree.evaluate();
"""
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
    protected String val;
    protected Node left;
    protected Node right;
};

class MyNode extends Node {
    public MyNode(String val) {
        this.val = val;
    }

    public int evaluate() {
        if (isNumeric()) {
            return Integer.parseInt(val);
        }
        int leftVal = left.evaluate();
        int rightVal = right.evaluate();
        if (Objects.equals(val, "+")) {
            return leftVal + rightVal;
        }
        if (Objects.equals(val, "-")) {
            return leftVal - rightVal;
        }
        if (Objects.equals(val, "*")) {
            return leftVal * rightVal;
        }
        if (Objects.equals(val, "/")) {
            return leftVal / rightVal;
        }
        return 0;
    }

    public boolean isNumeric() {
        for (char c : val.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Deque<MyNode> stk = new ArrayDeque<>();
        for (String s : postfix) {
            MyNode node = new MyNode(s);
            if (!node.isNumeric()) {
                node.right = stk.pop();
                node.left = stk.pop();
            }
            stk.push(node);
        }
        return stk.peek();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */
```

### **C++**

```cpp
/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

class Node {
public:
    virtual ~Node () {};
    virtual int evaluate() const = 0;
protected:
    // define your fields here
    string val;
    Node* left;
    Node* right;
};

class MyNode : public Node {
public:
    MyNode(string val) {
        this->val = val;
    }

    MyNode(string val, Node* left, Node* right) {
        this->val = val;
        this->left = left;
        this->right = right;
    }

    int evaluate() const {
        if (!(val == "+" || val == "-" || val == "*" || val == "/")) return stoi(val);
        auto leftVal = left->evaluate(), rightVal = right->evaluate();
        if (val == "+") return leftVal + rightVal;
        if (val == "-") return leftVal - rightVal;
        if (val == "*") return leftVal * rightVal;
        if (val == "/") return leftVal / rightVal;
        return 0;
    }
};


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
public:
    Node* buildTree(vector<string>& postfix) {
        stack<MyNode*> stk;
        for (auto s : postfix)
        {
            MyNode* node;
            if (s == "+" || s == "-" || s == "*" || s == "/")
            {
                auto right = stk.top();
                stk.pop();
                auto left = stk.top();
                stk.pop();
                node = new MyNode(s, left, right);
            }
            else
            {
                node = new MyNode(s);
            }
            stk.push(node);
        }
        return stk.top();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder* obj = new TreeBuilder();
 * Node* expTree = obj->buildTree(postfix);
 * int ans = expTree->evaluate();
 */
```

### **...**

```

```

<!-- tabs:end -->

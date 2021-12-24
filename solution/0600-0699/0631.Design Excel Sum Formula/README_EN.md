# [631. Design Excel Sum Formula](https://leetcode.com/problems/design-excel-sum-formula)

[中文文档](/solution/0600-0699/0631.Design%20Excel%20Sum%20Formula/README.md)

## Description

<p>Your task is to design the basic function of Excel and implement the function of sum formula.  Specifically, you need to implement the following functions:</p>

<p><code>Excel(int H, char W):</code> This is the constructor. The inputs represents the height and width of the Excel form. <b>H</b> is a positive integer, range from 1 to 26. It represents the height. <b>W</b> is a character range from 'A' to 'Z'. It represents that the width is the number of characters from 'A' to <b>W</b>. The Excel form content is represented by a height * width 2D integer array <code>C</code>, it should be initialized to zero. You should assume that the first row of <code>C</code> starts from 1, and the first column of <code>C</code> starts from 'A'.</p>

<br>

<p><code>void Set(int row, char column, int val):</code> Change the value at <code>C(row, column)</code> to be val.</p>

<br>

<p><code>int Get(int row, char column):</code> Return the value at <code>C(row, column)</code>.</p>

<br>

<p><code>int Sum(int row, char column, List of Strings : numbers):</code> This function calculate and set the value at <code>C(row, column)</code>, where the value should be the sum of cells represented by <code>numbers</code>. This function return the sum result at <code>C(row, column)</code>. This sum formula should exist until this cell is overlapped by another value or another sum formula.</p>

<p><code>numbers</code> is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, then it has the following format : <code>ColRow</code>. For example, "F7" represents the cell at (7, F). </p>

<p>If the string represent a range of cells, then it has the following format : <code>ColRow1:ColRow2</code>. The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, and ColRow2 represents the position of the bottom-right cell. </p>

<br>

<p><b>Example 1:</b><br />

<pre>

Excel(3,"C"); 

// construct a 3*3 2D array with all zero.

//   A B C

// 1 0 0 0

// 2 0 0 0

// 3 0 0 0



Set(1, "A", 2);

// set C(1,"A") to be 2.

//   A B C

// 1 2 0 0

// 2 0 0 0

// 3 0 0 0



Sum(3, "C", ["A1", "A1:B2"]);

// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4. 

//   A B C

// 1 2 0 0

// 2 0 0 0

// 3 0 0 4



Set(2, "B", 2);

// set C(2,"B") to be 2. Note C(3, "C") should also be changed.

//   A B C

// 1 2 0 0

// 2 0 2 0

// 3 0 0 6

</pre>

</p>

<p><b>Note:</b><br>

<ol>

<li>You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).</li>

<li> The test cases are using double-quotes to represent a character.</li>

<li>Please remember to <b>RESET</b> your class variables declared in class Excel, as static/class variables are <b>persisted across multiple test cases</b>. Please see <a href="https://leetcode.com/faq/#different-output">here</a> for more details.</li>

</ol>

</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**

```

```

<!-- tabs:end -->

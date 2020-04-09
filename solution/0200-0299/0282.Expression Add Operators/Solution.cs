using System;
using System.Collections.Generic;

public class Expression
{
    public long Value;

    public override string ToString()
    {
        return Value.ToString();
    }
}

public class BinaryExpression : Expression
{
    public char Operator;

    public Expression LeftChild;
    public Expression RightChild;

    public override string ToString()
    {
        return string.Format("{0}{1}{2}", LeftChild, Operator, RightChild);
    }
}

public class Solution {
    public IList<string> AddOperators(string num, int target) {
        var results = new List<string>();
        if (string.IsNullOrEmpty(num)) return results;
        this.num = num;
        this.results = new List<Expression>[num.Length, num.Length, 3];
        foreach (var ex in Search(0, num.Length - 1, 0))
        {
            if (ex.Value == target)
            {
                results.Add(ex.ToString());
            }
        }
        return results;
    }

    private string num;
    private List<Expression>[,,] results;

    private List<Expression> Search(int left, int right, int level)
    {
        if (results[left, right, level] != null)
        {
            return results[left, right, level];
        }
        var result = new List<Expression>();
        if (level < 2)
        {
            for (var i = left + 1; i <= right; ++i)
            {
                List<Expression> leftResult, rightResult;
                leftResult = Search(left, i - 1, level);
                rightResult = Search(i, right, level + 1);
                foreach (var l in leftResult)
                {
                    foreach (var r in rightResult)
                    {
                        var newObjects = new List<Tuple<char, long>>();
                        if (level == 0)
                        {
                            newObjects.Add(Tuple.Create('+', l.Value + r.Value));
                            newObjects.Add(Tuple.Create('-', l.Value - r.Value));
                        }
                        else
                        {
                            newObjects.Add(Tuple.Create('*', l.Value * r.Value));
                        }
                        foreach (var newObject in newObjects)
                        {
                            result.Add(new BinaryExpression
                            {
                                Value = newObject.Item2,
                                Operator = newObject.Item1,
                                LeftChild = l,
                                RightChild = r
                            });
                        }
                    }
                }
            }
        }
        else
        {
            if (left == right || num[left] != '0')
            {
                long x = 0;
                for (var i = left; i <= right; ++i)
                {
                    x = x * 10 + num[i] - '0';
                }
                result.Add(new Expression
                {
                    Value = x
                });
            }
        }
        if (level < 2)
        {
            result.AddRange(Search(left, right, level + 1));
        }
        return results[left, right, level] = result;
    }
}
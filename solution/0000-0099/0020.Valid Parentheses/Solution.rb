# @param {String} s
# @return {Boolean}
def is_valid(s)
  stk = []
  d = { '(' => ')', '[' => ']', '{' => '}' }
  s.each_char do |c|
    if d.key?(c)
      stk.push(d[c])
    elsif stk.empty? || stk.pop != c
      return false
    end
  end
  stk.empty?
end

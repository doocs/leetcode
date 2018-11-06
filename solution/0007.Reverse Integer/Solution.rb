# @param {Integer} x
# @return {Integer}
def reverse(x)
  neg = x < 0

  x = x.abs
  s = ''

  x /= 10 while x > 0 && (x % 10).zero?

  while x > 0
    s += (x % 10).to_s
    x /= 10
  end

  s = neg ? '-' + s : s

  # have to explicitly constraint the int boundary as per the dummy test case
  res = s.to_i
  res <= 214_748_364_7 && res >= -214_748_364_8 ? res : 0
end

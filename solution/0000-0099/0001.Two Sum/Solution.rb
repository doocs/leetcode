# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum(nums, target)
    d = {}
    nums.each_with_index do |x, i|
      y = target - x
      if d.key?(y)
        return [d[y], i]
      end
      d[x] = i
    end
end

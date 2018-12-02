from difflib import SequenceMatcher
from itertools import permutations

a = ''
b = ''
largest_ratio = 0.0

for perms in list(permutations(open('input.txt', 'r', 1), 2)):
    current_ratio = SequenceMatcher(None, perms[0], perms[1]).ratio()
    if (current_ratio > largest_ratio):
        largest_ratio = current_ratio
        a = perms[0]
        b = perms[1]

result = ''
for index, char in enumerate(a):
    if char == b[index]:
        result += char

print('ID\'s most alike:', a, b, largest_ratio)
print('Resulting match:', result)
from difflib import SequenceMatcher
from itertools import permutations

inventory_file = open('input.txt', 'r', 1)
inventory_list = inventory_file.read().splitlines()
inventory_file.close()

a = ''
b = ''
largest_ratio = 0.0
id_perms = list(permutations(inventory_list, 2))

for perm in id_perms:
    current_ratio = SequenceMatcher(None, perm[0], perm[1]).ratio()
    if (current_ratio > largest_ratio):
        largest_ratio = current_ratio
        a = perm[0]
        b = perm[1]

result = ''
for index, char in enumerate(a):
    if char == b[index]:
        result += char

print('ID\'s most alike:', a, b, largest_ratio)
print('Resulting match:', result)
inventory_file = open('input.txt', 'r', 1)
inventory_list = inventory_file.read().splitlines()
inventory_file.close()

count_dict = {}
for index, word in enumerate(inventory_list):
    count_dict.update({index : {}})
    for char in word:
        count = word.count(char)
        count_dict[index].update({char : count})

two_kind = 0
three_kind = 0
for char_counts in count_dict.values():
    count_list = char_counts.values()
    if (2 in count_list): 
        two_kind += 1
    if (3 in count_list):
        three_kind += 1

print('Checksum:', two_kind * three_kind)
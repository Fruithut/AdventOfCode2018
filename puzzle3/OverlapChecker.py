import numpy
from collections import defaultdict

def getInput(file_path: str):
    file_content = []
    for string in open(file_path, 'r', 1):
        values = string.split(" ")
        first_pair = values[2].split(',')
        second_pair = values[3].split('x')
        file_content.append([
            int(first_pair[0]), int(first_pair[1][:-1]), 
            int(second_pair[0]), int(second_pair[1][:-1])
        ])
    return file_content

# PART ONE
def find_multiclaim(claim_specifications: [[]]):
    claim_matrix = numpy.zeros((1000,1000))
    for x, y, width, height in claim_specifications:
        for x_offset in range(width):
            for y_offset in range(height):
                claim_matrix[x + x_offset][y + y_offset] += 1
    return sum(map(lambda num: 1 if num > 1 else 0, numpy.nditer(claim_matrix)))

contents = getInput('input.txt')
print(find_multiclaim(contents))

# PART TWO TODO
def checkOverlap(l1: tuple, r1:tuple, l2: tuple, r2:tuple):
    if (l1[0] > r2[0] or l2[0] > r1[0]):
        return False
    if (l1[1] < r2[1] or l2[1] < r1[1]):
        return False
    return True

def find_non_overlapping(claim_specifications: [[]]):
    id_hitlist = defaultdict(int)

    for index1, (x, y, width, height) in enumerate(claim_specifications):
        left_top1 = (x, y)
        right_bottom1 = (x + width, y + height)
        print(right_bottom1)

        for index2, (x2, y2, width2, height2) in enumerate(claim_specifications, start = index1 + 1):
            left_top2 = (x2, y2)
            right_bottom2 = (x2 + width2, y2 + height2)

            if checkOverlap(left_top1, right_bottom1, left_top2, right_bottom2):
                id_hitlist[index1] += 1
                id_hitlist[index2] += 1

    # return {k: v for k, v in id_hitlist.items() if v < 1}

# print(find_non_overlapping(contents))
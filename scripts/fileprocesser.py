import pandas as pd


class RunFiles:

    def extractMeta(self):
        left_str = self.name.split("_")[0]
        right_str = self.name.split("_")[1]
        names = left_str.split("-")
        if "E-" not in right_str:
            values = [right_str.split("-")]
        else:
            v = right_str.split("-")
            nrAsStr = v[1] +"-"+ v[2]
            nrAsFloat = float(nrAsStr)
            values = [[v[0],str(nrAsFloat), v[3]]]

        df = pd.DataFrame(values, columns=names)

        df["sum"] = sum(self.col_sum_list) / len(self.col_sum_list)
        df["mean"] = sum(self.col_mean_list) / len(self.col_mean_list)
        return df

    def extractMeanSumInfections(self):
        col_sum_list = []
        col_mean_list = []
        for df in self.dfs:
            col = df[self.s]
            col_sum = sum(col)
            col_mean = float(col_sum) / float(len(col))
            col_sum_list.append(col_sum)
            col_mean_list.append(col_mean)
        return col_sum_list, col_mean_list

    def extractMeanRow(self):
        col_list = pd.DataFrame()
        for df in self.dfs:
            col = df[self.s]
            col_list.insert(loc= col_list.shape[1], column=col_list.shape[1], value=col)
        col_list[self.name] = col_list.sum(axis=1)
        col_list[self.name] = col_list[self.name].astype(float)
        col_list[self.name] = col_list[self.name] / float(col_list.shape[1]-1)
        if "population" in self.name :
            pop = int(self.name.split('_')[1])
            col_list[str(pop)] = col_list[self.name] / pop
            self.name = self.name.split('_')[1]
            return col_list[str(pop)]
        elif "probability" in self.name:
            prob = float(self.name.split('_')[1])
            col_list[str(prob)] = col_list[self.name]
            self.name = self.name.split('_')[1]
            return col_list[str(prob)]
        elif "allreadyinfected-allreadytested" in self.name:
            prob = str(self.name.split('_')[1])
            col_list[str(prob)] = col_list[self.name]
            self.name = self.name.split('_')[1]
            return col_list[str(prob)]

        return col_list[self.name]

    def getSum(self):
        return self.col_sum_list

    def getMean(self):
        return self.col_mean_list

    def getRow(self):
        return self.row

    def getMeanCol(self):
        return self.meancol

    def getName(self):
        return self.name

    def __init__(self, dfs, name, s):
        self.name = name
        self.dfs = dfs
        self.s = s
        self.col_sum_list, self.col_mean_list = self.extractMeanSumInfections()
        self.row = self.extractMeta()
        self.meancol = self.extractMeanRow()

    def print(self):
        print(self.name)
        print(self.dfs)

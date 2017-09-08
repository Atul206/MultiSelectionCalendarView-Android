INFERANALYZE = '/usr/local/Cellar/infer/0.12.0_1/lib/infer/infer/bin/infer-analyze' --results-dir '/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out' 

CLUSTERS=cl1 cl2 cl3 cl4 cl5 

default: test

all: test

test: $(CLUSTERS)
	@echo

#/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/captured/CalendarDateManager.java.ff4dd899e6b87ceb63293602a983381a
cl1: 
	$(INFERANALYZE) --cluster '/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/multicore/xcl1.cluster'
	@touch $@

#/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/captured/MultiSelectionCalendarView.java.6353508fa90cdf25633a6d9932262c3d
cl2: 
	$(INFERANALYZE) --cluster '/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/multicore/xcl2.cluster'
	@touch $@

#/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/captured/NonRangeAdapter.java.b4de51cc7f876d7ff19e73afedadda5f
cl3: 
	$(INFERANALYZE) --cluster '/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/multicore/xcl3.cluster'
	@touch $@

#/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/captured/RangeAdapter.java.4b79c9241d155288b1b06f05c8967b18
cl4: 
	$(INFERANALYZE) --cluster '/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/multicore/xcl4.cluster'
	@touch $@

#/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/captured/SampleCalendarActivity.java.df0f1f87bb53f9a65bdd3e79349c9855
cl5: 
	$(INFERANALYZE) --cluster '/Users/atulsakhala/Documents/MultSelectionCalendarView/infer-out/multicore/xcl5.cluster'
	@touch $@


clean:
	rm -f $(CLUSTERS)

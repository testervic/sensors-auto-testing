# echo "执行shell："
# chmod 755 /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/res/chromedriver_linux_v72
# cd /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI
# start_time=`date +%s`
# echo $start_time
# /home/tools/apache-maven-3.5.4/bin/mvn test -Dsurefire.suiteXmlFiles=/var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/res/ddm.xml
# run_time=$(($(date +%s) - $start_time))
# echo "耗时：$run_time s" 
# sleep 5
report_path="/var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json"
declare -i i=1
while [ ! -f $report_path ] && ((i<=10))
do
	echo "i= $i"
	echo `date`
	sleep 3
	let ++i
done
echo "验证测试结果..."
cd /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/export
echo `pwd`
total=`jq .statistic.total /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
passed=`jq .statistic.passed /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
failed=`jq .statistic.failed /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
broken=`jq .statistic.broken /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
start=`jq .time.start /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
stop=`jq .time.stop /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
duration=`jq .time.duration /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
maxDuration=`jq .time.maxDuration /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
sumDuration=`jq .time.sumDuration /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
skipped=`jq .statistic.skipped /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target/allure-report/widgets/summary.json`
starts=`expr $start / 1000`
stops=`expr $stop / 1000`
maxDurations=`expr $maxDuration / 1000 `
sumDurations=`expr $sumDuration / 1000 `
durations=`expr $duration / 1000 `
start_date=`echo $starts | awk '{print strftime ("%T",$0)}'`
stop_date=`echo $stops | awk '{print strftime ("%T",$0)}'`
maxDuration_date=`echo $maxDurations | awk '{print strftime ("%T",$0)}'`
sumDuration_date=`echo $sumDurations | awk '{print strftime ("%T",$0)}'`
JobEndTime=`date "+%m-%d-%H:%M:%S"`
thread_count=`grep thread-count /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/res/ddm.xml | tr -cd "[0-9]\n"`
echo "TotalCases：$total"
echo "Passed：$passed"
echo "Failed：$failed"
echo "Broken：$broken"
echo "StartTime：$start_date"
echo "StopTime：$stop_date"
echo "MaxDuration：$maxDurations"
echo "SumDuration：$sumDurations"
echo "---------------------------------------------------"
if [ $failed -eq 0 ] && [ $broken -eq 0 ] && [ $passed -gt 0 ]
	then
		echo "测试通过！"
		#绫云告警群
		# curl 'https://oapi.dingtalk.com/robot/send?access_token=fcf772f39250d727beee44bcd648bf95e6ff9b9f0ee1211f0e178e07b5616c02' \
		#成都测试群
		# curl 'https://oapi.dingtalk.com/robot/send?access_token=27f94a1b9dbd2ce117db2b215c60e51e00a1969431efdc8f08027fe2094e7576' \
		#debug
		# https://oapi.dingtalk.com/robot/send?access_token=5a6db2b734eeb5ce28402fadfe42a38267900bd5b7f4a6417abab5a45fe1ed6f
		#自动化推进小组
		#https://oapi.dingtalk.com/robot/send?access_token=79a27ab6b0464291227cfd66b96b5eba838e763ed7da4a6699d09d6358f66ca2
		curl 'https://oapi.dingtalk.com/robot/send?access_token=79a27ab6b0464291227cfd66b96b5eba838e763ed7da4a6699d09d6358f66ca2' \
	   -H 'Content-Type: application/json' \
	   -d '{
		     "msgtype": "markdown",
		     "markdown": {
		         "title":"图档UI巡检报告",
		         "text": "#### 图档UI巡检报告:\n> ## SUCCEED!\n> ##### TotalCases = '${total}'\n> ##### Passed = '${passed}'\n> ##### Skipped = '${skipped}'\n> ##### Failed = '${failed}'\n> ##### Broken = '${broken}'\n> ##### StartTime = '${start_date}'\n> ##### StopTime = '${stop_date}'\n> ##### Duration = '${durations}'s\n> ##### MaxDuration = '${maxDurations}'s\n> ##### SumDuration = '${sumDurations}'s\n> #### ThreadCount = '${thread_count}'\n> [点击查看](http://47.99.179.79:10080/job/DDM.AutoUITest/) \n"
		     },
		      "at": {
		          "atMobiles": ["18681378808"],
		          "isAtAll": false
		      }
			 }'
    cd /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target
    #删除测试报告
    rm -rf allure-*
		exit 0
	else
		echo "测试未通过！请查看测试报告..."
		curl 'https://oapi.dingtalk.com/robot/send?access_token=5a6db2b734eeb5ce28402fadfe42a38267900bd5b7f4a6417abab5a45fe1ed6f' \
	   -H 'Content-Type: application/json' \
	   -d '{
		     "msgtype": "markdown",
		     "markdown": {
		         "title":"图档UI巡检报告",
		         "text": "#### 图档UI巡检报告:\n> ## FAILED!\n> ##### TotalCases = '${total}'\n> ##### Passed = '${passed}'\n> ##### Skipped = '${skipped}'\n> ##### Failed = '${failed}'\n> ##### Broken = '${broken}'\n> ##### StartTime = '${start_date}'\n> ##### StopTime = '${stop_date}'\n> ##### Duration = '${durations}'s\n> ##### MaxDuration = '${maxDurations}'s\n> ##### SumDuration = '${sumDurations}'s\n> #### ThreadCount = '${thread_count}'\n> [点击查看](http://47.99.179.79:10080/job/DDM.AutoUITest/) \n"
          },
		      "at": {
		          "atMobiles": ["18681378808","18693172275","17515760331","15090658719"],
		          "isAtAll": false
		      }
			 }'
    cd /var/lib/jenkins/workspace/DDM.AutoUITest/AutoUI/target
    #删除测试报告
    rm -rf allure-*
	  exit 1
fi
package com.robot.baselibs.common.network

import com.robot.baselibs.App

/**
 * describe 网络资源链接
 * authors lvuchenLiu
 * createTime 2019/8/13 18:08
 *
 * modifier
 * endTime 2019/8/13 18:08
 */

object NetWorkUrl {

    //顾凡本地链接
//    const val API = "http://47.240.40.115"
    //莫龙本地链接
//    const val API = "http://192.168.0.109:8881"
// 伊杰本地链接
//    const val API = "http://192.168.1.9:8881"
// 王伟成本地链接
//    const val API = "http://192.168.0.112:8881"


    private val CLIENT = "${App.API}.${App.URL}.com"
    val WS = "${App.API}.${App.URL}.com:8822"
    private val STIC = "${App.STIC}.${App.URL}.com"
    val API = "${App.HTTP}://${CLIENT}:8881"

    //注册滑块
    val verification = "http://${STIC}/html5/verification/verification.html"
    //篮球规则
    val bastetballRuleH5 = "http://${STIC}/rule/basketball.html"

    // 11选5玩法地址
    var elevenChoiceFiveRuleH5 = "http://${STIC}/rule/numLottery11-5.html"
    //排3
    var elevenChoiceFiveRuleP3 = "http://${STIC}/rule/pThree.html"
    //排5
    var elevenChoiceFiveRuleP5 = "http://${STIC}/rule/pFive.html"

    //山东
    var elevenChoiceFiveSDH5 = "https://m.chart.ydniu.com/trend/syx5sd/"
    //浙江
    var elevenChoiceFiveZJH5 = "https://m.chart.ydniu.com/trend/syx5zj/"
    //广东
    var elevenChoiceFiveGDH5 = "https://m.chart.ydniu.com/trend/syx5gd/"
    //    //山东
//    var elevenChoiceFiveSDH5 = "https://www.55128.cn/zs/80_467.htm"
//    //浙江
//    var elevenChoiceFiveZJH5 = "https://www.55128.cn/zs/85_512.htm"
//    //广东
//    var elevenChoiceFiveGDH5 = "https://www.55128.cn/zs/72_396.htm"
    //https://m.chart.ydniu.com/trend/syx5sd/ds.html
    //
    //排列5
    var pl5 = "https://m.chart.ydniu.com/trend/pl5/"
    //排列3
    var pl3 = "https://m.chart.ydniu.com/trend/pl3/"
    //足球规则
    val footballRuleH5 = "http://${STIC}/rule/soccer.html"
    //胜负十四规则
    val fourteenRuleH5 = "http://${STIC}/rule/ren14.html"

    var HELP_URL = "http://${App.STIC}.${App.URL}.com/rule/help/"

    //北单规则
    val bdRuleH5 = "http://${STIC}/rule/bdSoccer.html "

    //分享
    val SHARE_URL = "http://${STIC}/orderShare/index.html?oid="

    //分享赛事解析，新闻，视频
    val SHARE_URL_ANALYSIS = "http://${STIC}/newsShare/index.html?id="
    //任选九规则
    val nineRuleH5 = "http://${STIC}/rule/ren9.html"
    //足球动画
    val footballDT = "http://${STIC}/liveAnimate/footballLive/live.html"
    //篮球动画
    val bastballDT = "http://${STIC}/liveAnimate/basketballLive/bskFlash.html"

    //客服
    var kefu = "https://tb.53kf.com/code/app/10194647/1"

    //获取篮球理论奖金与注数
    val GETBASKETBALLBUONS = "http://${CLIENT}:5001/getBasketballBuons"

    //获取足球理论奖金与注数
    val GETCALCULATEBUONS = "http://${CLIENT}:5001/getCalculateBuons"

    //获取北单理论奖金与注数
    val GET_BD_BUONS = "http://${STIC}:5001/getBdBuons"

    private const val client = "/client"

    //用户注册
    const val REGISTERAPPUSER = "$client/user/index/registerAppUser"

    //发送短信验证码
    const val SENDAUTHCODE = "$client/user/index/sendAuthCode"

    //用户登录
    const val APPUSERLOGIN = "$client/user/index/AppUserLogin"

    //个人中心
    const val PERSONALCENTER = "$client/user/info/personalCenter"

    //足球赛事期号
    const val FOOTBALLISSUE = "$client/football/issue"

    //北单赛事期号
    const val BD_ISSUE = "$client/bd/issue"

    //北单列表接口
    const val BD_ODDS = "$client/bd/odds"

    //获取足球赛事接口
    const val FOOTBALLODDS = "$client/football/odds"

    //竞彩篮球
    const val BASKETBALLODDS = "$client/basketball/odds"

    //竞彩足球-赛事筛选
    const val EVENTLEAGUE = "$client/football/eventLeague"

    //北单-赛事筛选
    const val BD_GETLNAME = "$client/bd/getLName"

    //个人信息
    const val BASICINFORMATION = "$client/user/info/basicInformation"

    //我的订单
    const val MYORDER = "$client/order/myOrder"

    //合买 我的订单
    const val GET_GROUP_ORDER = "$client/order/getGroupOrder"

    //上传头像
    const val UPLOADPORTRAIT = "$client/user/info/uploadPortrait"

    //上传图片
    const val UPLOAD_IMG_FILE = "$client/bbsGroup/file"

    //实名认证
    const val RNAUTHENTICATION = "$client/user/info/RNAuthentication"

    //修改密码
    const val CHANGEPASSWORD = "$client/user/info/changePassword"

    //忘记密码
    const val FORGETPASSWORD = "$client/user/info/forgetPassword"

    //用户角色列表
    const val USERROLELIST = "$client/user/info/userRoleList"

    //修改默认角色
    const val CHANGEDEFAULTROLE = "$client/user/info/changeDefaultRole"

    //添加用户角色
    const val ADDUSERROLE = "$client/user/info/addUserRole"

    //获取银行卡信息列表
    const val GETLISTBANKINFO = "$client/user/info/getListBankInfo"

    //扫描银行卡信息
    const val SCANBANKCARD = "$client/user/info/scanBankCard"

    //绑定银行卡
    const val BINDBANKCARD = "$client/user/info/bindBankCard"

    //设置默认银行卡
    const val DEFAULTBANKCARD = "$client/user/info/defaultBankCard"

    //解绑银行卡
    const val UNBINDBANKCARD = "$client/user/info/unBindBankCard"

    //下单(统一)
    const val PLACEORDER = "$client/order/placeOrder"

    //任9 任14赛事
    const val LOTCTZQMATCHALL = "$client/lotCtzq/lotCtzqMatchAll"

    //获取注数
    const val GETMULTIPLYING = "$client/order/getMultiplying"

    //竞足单关
    const val ONEFOOTBALLODDS = "$client/football/oneFootballOdds"

    //订单详情
    const val ORDERDETAILS = "$client/order/orderDetails"

    //合买订单的票详情
    const val GET_COOPERATION_BUY = "$client/partnershipBuy/getCooperationBuy"

    // 合买详情
    const val GET_COOPERATION_BUY_DETAILS = "$client/partnershipBuy/getCooperationBuyDetail"
    //11获取11选5订单详情
    const val GET_ELEVEN_SELECTED_FIVE_DETAILS = "$client/order/getElevenSelectedFiveDetails"

    //合买
    const val INVOLVED_PARTNERSHIP_BUY = "$client/partnershipBuy/involvedPartnershipBuy"
    //出票详情
    const val TICKETDETAILS = "$client/order/ticketDetails"

    //发单
    const val SENDORDER = "$client/order/sendOrder"

    //开团
    const val CREATE_GROUP = "$client/order/createGroup"

    //跟单列表
    const val GETFOLLOWORDERPAGE = "$client/order/getFollowOrderPage"

    //复制跟单
    const val FOLLOWORDER = "$client/order/followOrder"

    //邀请进度
    const val LNVITEFRIENDSCODE = "$client/invite/inviteFriendsCode"

    //未完成下单和邀请成功
    const val SELECTCUINVITEFRIENDS = "$client/invite/selectCuInviteFriends"

    //发订单红包
    const val FAORDERROJO = "$client/rojo/faOrderRojo"

    //领红包
    const val GETFOLLOWORDERROJO = "$client/rojo/getFollowOrderRojo"

    //领取红包
    const val GET_ROJO = "$client/rojo/getRojo"

    //获取红包详情
    const val GETROJOINFO = "$client/rojo/getRojoInfo"

    //红包列表
    const val GETMYROJOLIST = "$client/rojo/getMyRojoList"

    //获取红包的年份
    const val GETROJOYEARS = "$client/rojo/getRojoYears"

    //大神首页列表
    const val AGREATGODHOME = "$client/order/aGreatGodHome"

    //大神合买列表
    const val GET_CHIPPED_LIST = "$client/partnershipBuy/getChippedList"


    //大神首页任务介绍以及关注
    const val GETGODHOME = "$client/order/getGodHome"

    //大神首页数据统计
    const val GODHOMEORDER = "$client/order/godHomeOrder"

    //大神首页合买数据统计
    const val GOD_GROUP_HOME_ORDER = "$client/order/godGroupHomeOrder"

    //开奖公告
    const val LOTTERYBULLETIN = "$client/bulletin/lotteryBulletin"

    //获取已经开奖的天数和场次
    const val getILotBDMatchLotteryList = "$client/bulletin/getILotBDMatchLotteryList"

    //北单开奖详情
    const val getBDLottery = "$client/bulletin/getBDLottery"

    //篮球期数
    const val LOTTERYBASKETBALLISSUE = "$client/bulletin/lotteryBasketballIssue"

    //篮球开奖赛事列表
    const val LOTTERYBASKETBALLLIST = "$client/bulletin/lotteryBasketballList"

    //足球期数
    const val LOTTERYFOOTBALLISSUE = "$client/bulletin/lotteryFootballIssue"

    //足球开奖赛事列表
    const val LOTTERYFOOTBALLLIST = "$client/bulletin/lotteryFootballList"

    //任9任14开奖列表
    const val LOTTERYCTZQISSUE = "$client/bulletin/lotteryCtzqIssue"

    //任9,任14开奖赛事详情
    const val LOTTERYCTZQPARTICULARS = "$client/bulletin/lotteryCtzqParticulars"

    //用户提现申请
    const val USERCAPITALAPPLY = "$client/capital/userCapitalApply"

    //获取转冲页面展示数据.可以转冲的余额以及优惠力度
    const val LIMITDISCOUNTS = "$client/capital/limitDiscounts"

    //转冲
    const val TRANSFERMONEY = "$client/capital/transferMoney"

    //关注新增
    const val INSERTFANS = "$client/cuRoleFans/insertFans"

    //根据跟随的订单id查询红包详情
    const val GETROJOINFOBYFOLLOWID = "$client/rojo/getRojoInfoByFollowId"

    //获取红包消息
    const val GETMSG = "$client/rojo/getMsg"

    //领取宝箱
    const val GETCHEST = "$client/invite/getChest"

    //获取自己的资金流水记录
    const val GETCAPITALJOURNAL = "$client/capital/getCapitalJournal"

    //获取大神主页的折线图
    const val GETGODHOMELINECHART = "$client/order/getGodHomeLineChart"

    // 获取大神主页合买折线图
    const val GET_GROUP_GOD_HOME_LINE_CHART = "$client/order/getGroupGodHomeLineChart"
    //获取热门大神的集合
    const val GETCOLL = "$client/cuAdminGreatgod/getColl"

    //获取榜单
    const val GETRANKINGLIST = "$client/order/getRankingList"

    //获取我的关注列
    const val GETMYFOCUSCOLL = "$client/cuRoleFans/getMyFocusColl"

    //即时比分数据(足球)
    const val SCOREDATA = "$client/live/foot/scoredata"

    //足球赛程赛果
    const val SCHEDULE = "$client/live/foot/schedule"

    //查询足球联赛
    const val GETLEAGUES = "$client/live/foot/getLeagues"

    //查询篮球联赛
    const val GETLEAGUES2 = "$client/live/basket/getLeagues"

    //即时比分数据
    const val SCOREDATA2 = "$client/live/basket/scoredata"

    //篮球赛程赛果
    const val SCHEDULE2 = "$client/live/basket/schedule"

    //直播-添加关注
    const val FOLLOWMATCH = "$client/matchLive/followMatch"

    //直播-取消关注
    const val UNFOLLOWMATCH = "$client/matchLive/unFollowMatch"

    //足球亚赔
    const val GETASIAODDS = "$client/live/foot/getAsiaOdds"

    //足球欧赔
    const val GETEUROPEODDS = "$client/live/foot/getEuropeOdds"

    //篮球亚赔
    const val GETASIAODDS2 = "$client/live/basket/getAsiaOdds"

    //篮球欧赔
    const val GETEUROPEODDS2 = "$client/live/basket/getEuropeOdds"

    //websocket token
    const val GETWSTOKEN = "$client/matchLive/getWsToken"

    //足球阵容
    const val GETLINEUP = "$client/live/foot/getLineup"

    //足球赛况
    const val GETFOOTFJSTATISTICS = "$client/live/foot/getFootFjStatistics"

    //文字直播
    const val GET_FIN_BY_MATCH_ID = "$client/live/letteringLive/finByMatchId"
    //篮球赛况
    const val GETACTION = "$client/live/basket/getAction"

    //篮球赛况-文字直播
    const val LIVETEXTBROADCAST = "$client/live/basket/liveTextBroadcast"

    //篮球赛况-数据统计
    const val GETDATASTATISTICS = "$client/live/basket/getDataStatistics"

    //会员中心-获取vip升级信息
    const val GETLEVELDATA = "$client/cuActivityReceiveInfo/getLevelData"

    //会员中心-获取会员权益
    const val GETMEMBERBENEFITS = "$client/cuActivityReceiveInfo/getMemberBenefits"

    //会员中心-获取用户任务列表,包含对应的是否已领.
    const val GETDAILYTASK = "$client/cuActivityReceiveInfo/getDailyTask"

    //会员中心-领取日常任务
    const val CUACTIVITYRECEIVEINFOINSERT = "$client/cuActivityReceiveInfo/insert"

    //会员中心-获取用户的积分列表
    const val GETACTIVITYRECEIVEINTEGRAL =
        "$client/cuActivityReceiveInfo/getActivityReceiveIntegral"

    //用户晒单
    const val USERORDERBASK = "$client/orderBask/userOrderBask"

    //晒单广场
    const val BASKPLAZA = "$client/orderBask/baskPlaza"

    //晒单点赞
    const val BASKLIKE = "$client/orderBask/baskLike"

    //评论列表
    const val USERCOMMENTLIST = "$client/interaction/userCommentList"

    //视频详情
    const val VIDEO_GET_DETAILS = "$client/informationVideos/getById"
    //用户评论
    const val USERCOMMENT = "$client/interaction/userComment"

    //足球阵容球员详细信息
    const val PLAYERDATA = "$client/live/foot/playerData"

    //用户点赞
    const val USERLIKE = "$client/interaction/userLike"

    //全民竞彩-方案详情
    const val PROJECTPARTICULARS = "$client/quiz/projectParticulars"

    //查询神单列表
    const val QUERYDIVINEORDER = "$client/divineOrder/queryDivineOrder"

    //首页-彩种玩法
    const val LOTCATEGORYLIST = "$client/user/index/lotCategoryList"

    //首页-banner跟公告的合集接口
    const val BANNERANDANNOUNCEMENT = "$client/sysAnnouncementInfo/bannerAndAnnouncement"

    //获取banner详情
    const val BANNER = "$client/sysBannerInfo/getById"

    //竞猜记录
    const val QUIZRECORD = "$client/quiz/quizRecord"

    //全民竞猜显示
    const val HOMEQUIZISSUE = "$client/quiz/homeQuizIssue"

    //北单单关
    const val BDSINGLEPASS = "$client/bd/bDSinglePass"

    //往期开奖结果
    const val PASTQUIZ = "$client/quiz/pastQuiz"

    //开奖结果
    const val QUIZLOTTERYRESULT = "$client/quiz/quizLotteryResult"

    //往期中奖名单
    const val PASTLIST = "$client/quiz/pastList"

    //中奖名单
    const val LOTTERYCALL = "$client/quiz/lotteryCall"

    //赛事信息
    const val QUIZMATCH = "$client/quiz/quizMatch"

    //全民竞彩-评论列表（弹幕）
    const val COMMENTLIST = "$client/quiz/commentList"

    //立即报名
    const val QUIZAPPLY = "$client/quiz/quizApply"

    //提交方案
    const val SUBMITTED = "$client/quiz/submitted"

    //全民竞彩-用户评论
    const val QUIZUSERCOMMENT = "$client/quiz/userComment"

    //每日任务-获取用户每日任务任务列表,包含对应的是否已领.
    const val GETSTHEDAILYTASKLIST = "$client/cuActivityReceiveInfo/getsTheDailyTaskList"

    //领取每日任务
    const val INSERTEVERYDAYTASK = "$client/cuActivityReceiveInfo/insertEverydayTask"

    //获取活动领取的乐乐币
    const val GETACTIVITIESREWARD = "$client/cuCapitalDetailInfo/getActivitiesReward"

    //获取活动领取的乐乐币的总额
    const val GETACTIVITIESREWARDSUM = "$client/cuCapitalDetailInfo/getActivitiesRewardSum"

    //获取新人任务列表
    const val GETSNEWPEOPLELIST = "$client/cuActivityReceiveInfo/getsNewPeopleList"

    //领取新人任务
    const val INSERTNEWPEOPLE = "$client/cuActivityReceiveInfo/insertNewPeople"

    //根据用户名字获取用户的角色信息
    const val ACCORDINGUSERNAMEGETTHEROLEOFINFORMATION =
        "$client/user/info/accordingUserNameGetTheRoleOfInformation"

    //获取我的消息列表
    const val GETMYMSG = "$client/cuMsg/getMyMsg"

    //删除消息
    const val DELBYMID = "$client/cuMsg/delByMid"

    //删除所有消息
    const val DELALL = "$client/cuMsg/delAll"

    //查看消息
    const val READMSG = "$client/cuMsg/readMsg"

    //获取今日赛事
    const val TODAYSCHEDULE = "$client/live/foot/todaySchedule"

    //获取咨询列表
    const val ADMINGETLIST = "$client/sysArticleInfo/appGetList"

    //视频
    const val VIDEO_GET_PAGE = "$client/informationVideos/getPage"

    //获取视频资讯分组
    const val gruoBydifference = "$client/sysInformation/getList"
    //获取文章列表
    const val APPGETLIST = "$client/sysEventInformationInfo/appGetList"


    //板块列表
    const val SELECT_PATE_NAME = "$client/plateName/selectPateName"
    //获取首页推荐
    const val SELECT_RECOMMEND_ARTICLE = "$client/plateName/selectRecommendArticle"
    //搜索文章
    const val ARTICLECOLLECTIONSEARCH = "$client/sysEventInformationInfo/articleCollectionSearch"

    //根据赛事编号查询直播数据(足球)
//    const val FOOTGETBYISSUNNO = "$client/live/foot/getByIssunNo"

    //根据赛事编号查询直播数据(篮球)
//    const val BASKETGETBYISSUNNO = "$client/live/basket/getByIssunNo"

    //根据赛事的赛期和比赛编号,查询赛事直播数据
    const val GET_BY_ISSUE_AND_PRE="$client/live/basket/getByIssueAndPreformance"
    //根据赛事id查询直播数据(篮球)
    const val BASKETGETBYID = "$client/live/basket/getOutById"

    //根据赛事id查询直播数据(足球)
    const val FOOTGETBYID = "$client/live/foot/getOutById"

    //支付通道列表
    const val PAYMENTCHANNEL = "$client/paymentByApp/paymentChannel"

    //提交线下充值
    const val OFFLINETRANSFER = "$client/paymentByApp/offlineTransfer"

    //查询线下收款账户
    const val QUERY_OFFLINE_BENEFICIARY = "$client/paymentByApp/queryOfflineBeneficiaryBank"
    //上传充值截图
    const val UPLOADRECHARGEPICTURE = "$client/paymentByApp/uploadRechargePicture"

    //发起支付请求
    const val PAYMENTBYAPP = "$client/paymentByApp/payment"

    //获取任务领取提醒
    const val GETREMIND = "$client/cuActivityReceiveInfo/getRemind"

    //新增意见反馈
    const val CUFEEDBACK = "$client/cuFeedback/save"

    //上传图片
    const val UPLOAD_FILE = "$client/imgFile/file"

    //举报内容
    const val REPORTCONTENT = "$client/interaction/reportContent"

    //用户举报
    const val USERREPORT = "$client/interaction/userReport"

    //查询社区列表
    const val GETBBSLIST = "$client/bbsInfoController/getBBSList"

    //获取社区的消息
    const val BBS_GETMYMSG = "$client/bbsInfoController/getMyMsg"

    //关注列表
    const val ATTENTIONLIST = "$client/bbsArticle/attentionList"

    //查询实单推荐列表
    const val RECOMMENDBYCLIENT = "$client/recommendByClient"

    //文章购买
    const val BUY_ARTICLE = "$client/bbsArticle/buyArticle"

    //发布文章
    const val SAVE_ARTICLE = "$client/bbsArticle/saveBbsArticle"

    //获取社区详情
    const val GET_BBS_DETAILS = "$client/bbsGroupUser/merge"

    //关注或取消文章
    const val ATTENTION_OR_CANCEL = "$client/bbsArticle/attentionOrCancel"

    //加入群
    const val JOIN_GROUP = "$client/bbsGroup/joinGroup"

    //退出群
    const val QUIT_GROUP = "$client/bbsGroup/quitGroup"

    // 获取群详情
    const val GET_GROUP_INFO = "$client/bbsGroup/getGroupInfo"

    // 获取群成员列表
    const val GET_USER_LIST = "$client/bbsGroupUser/getUserListByGid"

    //获取文章列表
    const val GET_ARTICLELIST = "$client/bbsArticle/getArticleList"

    //文章详情
    const val GET_ARTICLE = "$client/bbsArticle/getArticle"

    //获取群聊天记录
    const val GET_GROUP_TALK = "$client/bbsGroup/getGroupTalk"

    //创建红包返回红包id
    const val CREATE_ROJO = "$client/rojo/createRojo"

    //撤销聊天
    const val REVOCATION_MSG = "$client/bbsGroupUser/revocationMsg"

    //篮球历史交锋
    const val GETHISTOTYCONFRONTATION = "$client/live/basket/getHistoryConfrontation"

    //篮球历史战绩
    const val BASKETGETHISTORICALRECORD = "$client/live/basket/getHistoricalRecord"

    //    const val BASKETGETHISTORICALRECORD = "$client/live/basket/getBaskAgainst"
    //足球进球分布和联赛积分
    const val GETGOALANDLEAGUEPOINTS = "$client/live/foot/getGoalAndLeaguePoints"

    //足球历史交锋
    const val GETHISTORYCONFRONTATION = "$client/live/foot/getHistoryConfrontation"

    //获取交锋数据部分1
    const val GET_FOOT_AGAINST_PART_ONE = "$client/live/foot/getFootAgainstPartOne"
    //获取交锋数据部分2
    const val GET_FOOT_AGAINST_PART_TWO = "$client/live/foot/getFootAgainstPartTwo"
    //获取交锋数据部分3
    const val GET_FOOT_AGAINST_PART_THREE = "$client/live/foot/getFootAgainstPartThree"
    //足球情报
    const val FOOT_INJURY_NEW = "$client/live/footInjuryNew/findById"
    //足球历史战绩
    const val FOOTGETHISTORICALRECORD = "$client/live/foot/getHistoricalRecord"

    //11选5
    const val SELECT_ISSUE_INFO = "$client/elevenChoiceFiveByClient/selectIssueInfo"

    //获取预计得奖
    const val SELECT_PRIZE_ESTIMATE = "$client/elevenChoiceFiveByClient/selectPrizeEstimate"

    //11选5开奖列表
    const val ELEVEN_CHOICE_FIVE_LOTTERY = "$client/bulletin/elevenChoiceFiveLottery"

    //11选5开奖详情
    const val OPTIONAL_PARTICULARS_LOTTERY = "$client/bulletin/optionalParticularsLottery"

    //排列3
    const val THREE_GET_PLAYING = "$client/pThree/getPlaying"

    //排列5
    const val FIVE_GET_PLAYINNG = "$client/pFive/getInfo"

    //排列5公告详情
    const val GET_FIVE_LOTTERY = "$client/bulletin/pFiveLottery"

    //排列3公告详情
    const val GET_THREE_LOTTERY = "$client/pThree/getPThreeList"

    // 获取11选5开奖号码
    const val GET_ELEVEN_CHOICE_FIVE_LOTTERY = "$client/bulletin/getElevenChoiceFiveLottery"

    // 联赛列表
    const val FOOT_LEAGUELIST = "$client/live/foot/leagueList"

    //积分榜
    const val GET_JF = "$client/showData/getJf"

    //赛程
    const val GET_SC = "$client/showData/getSc"

    //年份
    const val YEAR_LIST = "$client/showData/getSeason"

    //获取轮
    const val GET_LUN_LIST = "$client/showData/getLunList"

    //球员榜
    const val PLAYER_LIST = "$client/live/foot/playersList"

    //球队榜
    const val TEAM_LIST = "$client/live/foot/teamList"

    //球员信息
    const val PLAYERS = "$client/live/player/players"

    //晒单列表tab
    const val GET_SHARE_TAB = "$client/orderBask/getShareTab"

    //查询所有彩种列表
    const val GET_ALL = "$client/lotCategory/getAll"
    //合买大厅
    const val GET_TOGETHER_BUY_LOBBY = "$client/partnershipBuy/getTogetherBuyLobby"

    //数据联赛列表
    const val DATA_LEAGUE_LIST = "$client/live/player/dataLeagueList"

    //球队数据联赛列表
    const val TEAM_DATA_LEAGUE_LIST = "$client/live/team/dataLeagueList"
    // 联赛数据
    const val GET_LEAGUE_DATA = "$client/live/player/getLeagueData"

    // 球队联赛数据
    const val GET_TEAM_LEAGUE_DATA = "$client/live/team/getLeagueData"

    //比赛
    const val MATCH_DATE = "$client/live/player/matchDate"

    //赛程
    const val TEAM_MATCH_DATE = "$client/live/team/matchDate"

    //球员转会记录
    const val FOOT_BALL_TRANS_FER_LIST = "$client/live/player/footballTransferList"

    // 职业生涯
    const val PLAYER_GET_CAREER = "$client/live/player/getCareer"
    //球队信息
    const val GET_FOOT_FJ_TEAM = "$client/live/team/getFootFjTeam"

    // 球队资料
    const val TEAM_GET_DATA = "$client/live/team/getData"

    //球队球员信息
    const val TEAM_GET_PLAYERS = "$client/live/team/getPlayers"

    //转会记录窗口列表
    const val TEAM_WINDOW_LIST = "$client/live/team/windowList"

    //转会记录
    const val GET_TRANSFER_LOG = "$client/live/team/getTransferLog"

    // 验证版本
    const val CHECK_APP_VERSION = "$client/user/index/checkAppVersion"

    //充值客服
    const val PREPAID_PHONE_CALL = "$client/user/info/prepaidPhoneCall"
}
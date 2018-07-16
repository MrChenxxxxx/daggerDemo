# daggerDemo
dagger基本用法


>Dagger2介绍

一般的IOC框架都是通过反射来实现的,但Dagger2作为Android端的IOC框架,为了不影响性能,它是通过apt动态生成代码来实现的.
Dagger2主要分为三个模块:

    依赖提供方Module,负责提供依赖中所需要的对象,实际编码中类似于工厂类
    依赖需求方实例,它声明依赖对象,它在实际编码中对应业务类,例如Activity,当你在Activity中需要某个对象时,你只要在其中声明就行,声明的方法在下面会讲到.
    依赖注入组件Component,负责将对象注入到依赖需求方,它在实际编码中是一个接口,编译时Dagger2会自动为它生成一个实现类.

Dagger2的主要工作流程分为以下几步:

    将依赖需求方实例传入给Component实现类
    Component实现类根据依赖需求方实例中依赖声明,来确定该实例需要依赖哪些对象
    确定依赖对象后,Component会在与自己关联的Module类中查找有没有提供这些依赖对象的方法,有的话就将Module类中提供的对象设置到依赖需求方实例中

通俗上来讲就好比你现在需要一件衣服,自己做太麻烦了,你就去商店买,你跟商店老板说明你想要购买的类型后,商店老板就会在自己的衣服供应商中查找有没有你所说的类型,有就将它卖给你.其中你就对应上面所说的依赖需求方实例,你只要说明你需要什么,商店老板则对应Component实现类,负责满足别人的需求,而衣服供应商则对应Module类,他负责生产衣服.也许这里有点绕,但经过下面的Demo,也许能够帮助你理解.

## 一、引入Dagger2

>1、在app目录的build.gradle文件中添加
		

	dependencies {
   
    //引入dagger2
     compile 'com.google.dagger:dagger:2.4'
     annotationProcessor 'com.google.dagger:dagger-compiler:2.4'
    // java注解
     provided 'org.glassfish:javax.annotation:10.0-b28'

	}

>2、编写布料类Cloth

写一个Cloth类用作依赖对象,它包含一个color属性
>3、书写Module类

现在的需求是MainActivity中需要使用到Cloth对象,所以我们要为MainActivity书写一个Module类用来提供Cloth对象,相当于创建了一个提供商

	@Module
	public class MainModule {	

    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
	    }
	}

	

注解是Dagger2中的关键,编写Module类时要在该类上声明@Module以表明该类是Module类,这样Dagger2才能识别,那@Provides又是干嘛的呢?它的作用是声明Module类中哪些方法是用来提供依赖对象的,当Component类需要依赖对象时,他就会根据返回值的类型来在有@Provides注解的方法中选择调用哪个方法.在一个方法上声明@Provides注解,就相当于创建了一条生产线,这条生产线的产物就是方法的返回值类型.有了这条生产线,供应商就能提供这种类型的商品了,当商店老板发现有人需要这种类型的商品时,供应商就可以提供给他了

>4、书写Componen接口

	@Component(modules=MainModule.class)
	public interface MainComponent {
	    void inject(MainActivity mainActivity);
	}

和Module类一样,Component类也是需要注解声明的,那个注解就是@Component,但是@Component注解的作用可不是单单用来声明Component类,他还有更强大的功能,@Component注解有modules和dependencies两个属性,这两个属性的类型都是Class数组,modules的作用就是声明该Component含有哪几个Module,当Component需要某个依赖对象时,就会通过这些Module类中对应的方法获取依赖对象,MainComponent中只包含MainModule,所以令modules=MainModule.class,相当于供应商和商店老板确定合作关系的合同.而dependencies属性则是声明Component类的依赖关系,这个下面再详讲.

接口中那个方法又是干嘛用的呢?

我们现在只是声明了Component类,但我们要怎么将Component类和依赖需求方对象联合起来呢?答案就是通过这个inject方法,这个方法可以将依赖需求方对象送到Component类中,Component类就会根据依赖需求方对象中声明的依赖关系来注入依赖需求方对象中所需要的对象,本Demo中MainActivity中需要Cloth对象,所以我们通过inject方法将MainActivity实例传入到MainComponent中,MainComponent就会从MainModule中的getCloth方法获取Cloth实例,并将该实例赋值给MainActivity中的cloth字段.相当于你去商店的道路,没有这条路,你就无法去商店和老板说明你所需要的东西.**但是这里需要注意的是,inject方法的参数不能用父类来接收,例如本Demo中,如果inject的参数是Activity,那么Dagger2就会报错**

>在MainActivity中声明


	public class MainActivity extends Activity {
	
	    @Inject
	    Cloth cloth;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        TextView tvMessage = (TextView) findViewById(R.id.tv_message);
	        MainComponen build = DaggerMainComponen.builder().mainModule(new MainModule()).build();
	        build.inject(this);
	        tvMessage.setText("我现在有" + cloth);
	    }
	}

上面代码中有两处关键:

声明依赖对象Cloth,就是在cloth字段上添加@Inject注解,Dagger2中声明依赖对象都是通过@Inject注解,但是@Inject注解的字段不能是private和protected的.

通过Dagger2自动生成的类来创建Component的实现类,创建时需要传入该Component实现类所需要的Module类实例,传入方法就是调用Module类类名首字母小写对应的方法.这里我们通过Dagger2自动生成的DaggerMainComponent类创建了MainComponent的实例,相当于我们创建了一个实实在在的商店,不再是理论上的商店,但是创建商店一定也要创建真实的供应商嘛,所以创建Component实现类时一定要传入Module的实例.(注意编写完Component接口后Dagger2并不会自动创建对应的类,需要我们点击Android Studio中bulid菜单下的Rebulid Poject选项,或者直接书写代码,编译时Dagger2就会帮你自动生成).

再将MainActivity通过inject方法发送到MainComponent中,调用完inject方法后,你就会发现,MainActivity中的cloth字段已经被赋值,而且该cloth对应的就是我们在MainModule类getCloth方法中创建的Cloth对象.

	
`另外一种编写module的方法`

	public class Shoe {
	    @Inject
	    public Shoe() {
	
	    }
	
	    @Override
	    public String toString() {
	        return "鞋子";
	    }
	}

##tips:
在构造函数上声明了@Inject注解,这个注解有什么用呢?作用可大了,当Component在所拥有的Module类中找不到依赖需求方需要类型的提供方法时,Dagger2就会检查该需要类型的有没有用@Inject声明的构造方法,有则用该构造方法创建一个.

##注意
有些读者可能会这样想:为什么不都用这种方法来声明呢?为什么要用Module类?
答案是这样的,项目中我们会用到别人的jar包,我们无法修改别人的源码,就更别说在人家的类上添加注解了,所以我们只能通过Module类来提供.

---------------------
<font color="#dd0000" size="6">复杂一点的情况</font><br /> 

---------------------
我们创建的这些依赖类都不用依赖于其它依赖类,但是如果需要依赖于其它依赖类又要怎么弄呢?
>创建依赖类Clothes

我们又来创建一个衣服类Clothes,制作衣服时需要布料,所以我们在创建Clothes的实例时需要用到Cloth实例
	
	@Module
	public class MainModule {
	
	    @Provides
	    public Cloth getCloth() {
	        Cloth cloth = new Cloth();
	        cloth.setColor("红色");
	        return cloth;
	    }
	
	    @Provides
	    public Clothes getClothers1() {
	        Cloth cloth = new Cloth();
	        cloth.setColor("黄色");
	        return new Clothes(cloth);
	    }
	
	    @Provides
	    public Clothes getClothers2(Cloth cloth) {
	        return new Clothes(cloth);
	    }
	}

###tips：getClothers1 和getClothers2都加  @Provides这个注释的话会报以下错。说明方法名可以随便取，修复这个报错要加上@Named这个注解
	Error:(16, 10) 错误: com.cxg.skylele.app.model.Clothes is bound multiple times:
	@Provides com.cxg.skylele.app.model.Clothes com.cxg.skylele.app.dagger.module.MainModule.getClothers1()
	@Provides com.cxg.skylele.app.model.Clothes com.cxg.skylele.app.dagger.module.MainModule.getClothers2(com.cxg.skylele.app.model.Cloth)

依赖总结

同理,在带有@Inject注解的构造函数要是依赖于其它对象,Dagger2也会帮你自动注入.笔者就不测试了,希望读者亲测一下.
这里我们引用依赖注入神器：Dagger2详解系列中的一段话:

    我们有两种方式可以提供依赖，一个是注解了@Inject的构造方法，一个是在Module里提供的依赖，那么Dagger2是怎么选择依赖提供的呢，规则是这样的：

* 步骤1：查找Module中是否存在创建该类的方法。
* 步骤2：若存在创建类方法，查看该方法是否存在参数
* 步骤2.1：若存在参数，则按从步骤1开始依次初始化每个参数
* 步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
* 步骤3：若不存在创建类方法，则查找Inject注解的构造函数，看构造函数是否存在参数
* 步骤3.1：若存在参数，则从步骤1开始依次初始化每个参数
* 步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束

也就说Dagger2会递归的提供依赖.

>@Name 和 @Qulifier 使用

@Qulifier功能和@Named一样,并且@Named就是继承@Qulifier的,我们要怎么使用@Qulifier注解呢?答案就是自定义一个注解:

>@Singleton 和 @Scope 的使用

>组件依赖dependencies的使用

子类的作用域和父类的不能一样，要不然会报以下错误
	
	SecondComponent depends on scoped components in a non-hierarchical scope ordering:
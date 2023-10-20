//
//  ViewController.swift
//  sampleApp
//
//  Created by Mehmet Şahin Ayçiçek on 27.07.2022.
//

import UIKit
import Storyly


class ViewController: UIViewController {
    let storylyViewProgrammatic = StorylyView()
    let STORYLY_INSTANCE_TOKEN = "YOUR_TOKEN"
    
    
    
    let externalData = [[
        "{name}": "Sahin",
        "{user_id}": "sahin"
    ],
    [
        "{name}": "Alper",
        "{user_id}": "alper"
    ]]
    
    let userPropertiesData = [
          "user_id": "John",
          "country_count": "7",
          "mile_count": "2751",
          "cover_image": "https://w7.pngwing.com/pngs/114/579/png-transparent-pink-cross-stroke-ink-brush-pen-red-ink-brush-ink-leave-the-material-text.png"
        ]
    
    let storylyFlag = true
    
    let font = UIFont(name:"Roboto-BoldItalic", size: 32)
    
    private var lastStorylyView: StorylyView?

    @IBOutlet weak var storlyView: StorylyView!
       
    override func viewDidLoad() {
        super.viewDidLoad()
        let openStoryURL = "cianurl://cian.ru/storyly?g=38178&instance=9386&play=sg"
        let lang =  Locale.autoupdatingCurrent.languageCode
        print("lang ======== \(String(describing: lang!))")
        if (storylyFlag == true) {
            self.storlyView.storylyInit = StorylyInit(
                storylyId: STORYLY_INSTANCE_TOKEN,
                segmentation:StorylySegmentation(segments: Set(arrayLiteral: lang!, "диплинк", "french", "germany", "staging", "gender-f","active", "de")),
                customParameter: "2"
                //userData: userPropertiesData
            )
        }
        // Do any additional setup after loading the view.
        self.storlyView.rootViewController = self
        self.storlyView.delegate = self
        
        //self.storlyView.setExternalData(externalData: externalData)
        self.storlyView.storylyInit.userData = userPropertiesData
        self.storlyView.storyInteractiveFont = UIFont(name: "Thonburi-Light", size: 10)!
        self.storlyView.openStory(payload: URL(string: openStoryURL)!)
        //self.storlyView.openStory(storyGroupId: "51360", storyId: "418174", play: .Story)
        if (storylyFlag == false){
            self.storlyView.isHidden = true
        }
        //self.storlyView.storyGroupSize = "custom"
        //self.storlyView.storyGroupIconStyling = StoryGroupIconStyling(height: 100, width: 150, cornerRadius: 20)
        self.storlyView.hydrateProducts(products: products)
    }


}


extension ViewController: StorylyDelegate {
    func storylyActionClicked(_ storylyView: StorylyView, rootViewController: UIViewController, story: Story) {
        
        print("[storyly] IntegrationViewController:storylyActionClicked - story action_url {\(story.media.actionUrl ?? "")}")
                
        guard let url = URL(string: story.media.actionUrl! ) else {
            return
                }
        UIApplication.shared.openURL(url)
        
        
        
        /*self.lastStorylyView = storylyView
        self.lastStorylyView?.pause()
        let  bottomsheet = RedBottomSheet()
        
        if #available(iOS 15.0, *) {
            if let sheet = bottomsheet.sheetPresentationController {
                sheet.detents = [.medium()]
            }
        }
        topMostController()?.present(bottomsheet, animated: true,completion: nil)
        
        bottomsheet.onDismiss = {
            self.lastStorylyView?.resume()
        }*/
    }
    func storylyEvent(_ storylyView: Storyly.StorylyView,
                      event: Storyly.StorylyEvent,
                      storyGroup: Storyly.StoryGroup?,
                      story: Storyly.Story?,
                      storyComponent: Storyly.StoryComponent?) {
        print("seen ======== \(story!.seen)")
        print("StoryNextClicked ======== \(Storyly.StorylyEvent.StoryNextClicked.stringValue)")
        //print("StoryComponent ======== \(storyComponent?.type.stringValue)")
        print("Event Type ===== \(event.stringValue)")

        if (event == Storyly.StorylyEvent.StoryViewed ){
                        
            if( story?.media.type == StoryType.Video){
                
                //print("StoryComponent ======== \(storyComponent?.type.stringValue)")
                print("StoryComponent ======== Stop Music")
            }
            
            else{
                print("StoryComponent ======== Play Music")
            }
            
        }
        
        
        if (event == Storyly.StorylyEvent.StoryPromoCodeCopied ){
            
            print("EVENT --> \(Storyly.StorylyEvent.StoryPromoCodeCopied)")
            
        }
        
        /*if ( event == Storyly.StorylyEvent.StoryViewed) {
            print("Story Type ======== \(story?.media.type.rawValue.description)")
        }*/
        
    }
    
    func storylyStoryDismissed(_ storylyView: Storyly.StorylyView) {
        print("StoryComponent ======== Play Music")
    }
    
    func storylyLoaded(_ storylyView: StorylyView, storyGroupList: [StoryGroup], dataSource: StorylyDataSource) {
        print("storylyLoaded ==========>\(storyGroupList.capacity)-\(dataSource.rawValue.description)")
        
        if (dataSource == StorylyDataSource.API) {
            print("apii ======== \(StorylyDataSource.API)")
            print("storylyLoadedAPIIIII ==========>\(storyGroupList.count)")
                }
        
        
        /*if (dataSource == StorylyDataSource.API) {
            if(storyGroupList.count==0){
                self.storlyView.isHidden = true
            }
            else {
                self.storlyView.isHidden = false
            }
        }*/
        
        
        /*DispatchQueue.main.asyncAfter(deadline: .now() + 5.0) {
            print("5 saniye delayyyy")
            self.storlyView.openStory(storyGroupId: "51360", storyId: "418174", play: .Story)
         }*/
        
    }
    
    func storylyLoadFailed(_ storylyView: StorylyView, errorMessage: String) {
            print("[storyly] IntegrationViewController:storylyLoadFailed - error {\(errorMessage)}")
        }
    
    func storylyUserInteracted(_ storylyView: Storyly.StorylyView,
                               storyGroup: Storyly.StoryGroup,
                               story: Storyly.Story,
                               storyComponent: Storyly.StoryComponent) {
        
        print("storyComponent ======== \(storyComponent.type)")
        
        let promoCodeComponent = storyComponent as? StoryPromoCodeComponent
             
            print("storyComponent ======== \(promoCodeComponent?.text)")

    }
}
 
func topMostController() -> UIViewController? {
    guard let window = UIApplication.shared.keyWindow, let rootViewController = window.rootViewController else {
        return nil
    }
    var topController = rootViewController
    
    while let newTopController = topController.presentedViewController {
        topController = newTopController
    }
    return topController
}


extension ViewController: StorylyProductDelegate {
   
    func storylyEvent(_ storylyView: StorylyView, event: StorylyEvent, product: STRProductItem?, extras: [String : String]) {
        if(event == StorylyEvent.StoryAddToCartClicked) {
            //Write your addToCard method
            print("Product Added To Card")
            print("Product Details: \(String(describing: product))")
            print("Number of Product: \(extras)")
        }
        if(event == StorylyEvent.StoryGoToCartClicked) {
            //Write your addToCard method
            print("Go to Card Page")
        }
        if(event == StorylyEvent.StoryProductCatalogOpened) {
            print("Product Catalog Opened")
        }
        if(event == StorylyEvent.StoryProductCatalogClosed) {
            print("Product Catalog Closed")
        }
        if(event == StorylyEvent.StoryProductSelected) {
            print("Product Selected")
        }
    }
    
    func storylyHydration(_ storylyView: StorylyView, productIds: [String]) {
        print("storylyHydration, productIds: \(productIds)")
    }
}



let products = [
     STRProductItem(
         productId:"1",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#6D7868"),
             STRProductVariant(name:"size",value:"XS")
         ]
     ),
     STRProductItem(
         productId:"3",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#6D7868"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"4",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#6D7868"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"5",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#6D7868"),
             STRProductVariant(name:"size",value:"XL")
         ]
     ),
     STRProductItem(
         productId:"6",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#282025"),
             STRProductVariant(name:"size",value:"XS")
         ]
     ),
     STRProductItem(
         productId:"7",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#282025"),
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"8",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#282025"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"9",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#282025"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"10",
         productGroupId:"1",
         title:"High-waist midi skirt",
         url:"https://www.storyly.io/",
         description:"High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-1/1-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-282025.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-282025.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#282025"),
             STRProductVariant(name:"size",value:"XL")
         ]
     ),
     STRProductItem(
         productId:"11",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#171614"),
             STRProductVariant(name:"size",value:"XS")
         ]
     ),
     STRProductItem(
         productId:"12",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#171614"),
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"13",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#171614"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"14",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#171614"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"15",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-171614.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-171614.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#171614"),
             STRProductVariant(name:"size",value:"XL")
         ]
     ),
     STRProductItem(
         productId:"16",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#A5B9DE"),
             STRProductVariant(name:"size",value:"XS")
         ]
     ),
     STRProductItem(
         productId:"17",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#A5B9DE"),
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"18",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#A5B9DE"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"19",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#A5B9DE"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"20",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-A5B9DE.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-A5B9DE.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#A5B9DE"),
             STRProductVariant(name:"size",value:"XL")
         ]
     ),
     STRProductItem(
         productId:"21",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#CFBAD9"),
             STRProductVariant(name:"size",value:"XS")
         ]
     ),
     STRProductItem(
         productId:"22",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#CFBAD9"),
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"23",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#CFBAD9"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"24",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#CFBAD9"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"25",
         productGroupId:"2",
         title:"Basic long-sleeve crop top",
         url:"https://www.storyly.io/",
         description:"Fitted elastic top with a round neck and long sleeves.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-2/1-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/2-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/3-CFBAD9.jpg","https://random-feed-generator.vercel.app/images/clothes/group-2/4-CFBAD9.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#CFBAD9"),
             STRProductVariant(name:"size",value:"XL")
         ]
     ),
     STRProductItem(
         productId:"26",
         productGroupId:"3",
         title:"Handbag with handle",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-3/1-1F1D20.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/2-1F1D20.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/3-1F1D20.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/4-1F1D20.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#1F1D20"),
         ]
     ),
     STRProductItem(
         productId:"27",
         productGroupId:"3",
         title:"Handbag with handle",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-3/1-F2EEE3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/2-F2EEE3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/3-F2EEE3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/4-F2EEE3.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#F2EEE3"),
         ]
     ),
     STRProductItem(
         productId:"28",
         productGroupId:"3",
         title:"Handbag with handle",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-3/1-C2D1B7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/2-C2D1B7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/3-C2D1B7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-3/4-C2D1B7.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#C2D1B7"),
         ]
     ),
     STRProductItem(
         productId:"29",
         productGroupId:"4",
         title:"High-heel mini platform boots",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-4/1-252726.jpg","https://random-feed-generator.vercel.app/images/clothes/group-4/2-252726.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#252726"),
             STRProductVariant(name:"size",value:"7,5")
         ]
     ),
     STRProductItem(
         productId:"31",
         productGroupId:"4",
         title:"High-heel mini platform boots",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-4/1-252726.jpg","https://random-feed-generator.vercel.app/images/clothes/group-4/2-252726.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#252726"),
             STRProductVariant(name:"size",value:"8,5")
         ]
     ),
     STRProductItem(
         productId:"33",
         productGroupId:"4",
         title:"High-heel mini platform boots",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-4/1-EBE5D5.jpg","https://random-feed-generator.vercel.app/images/clothes/group-4/2-EBE5D5.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#EBE5D5"),
             STRProductVariant(name:"size",value:"8")
         ]
     ),
     STRProductItem(
         productId:"34",
         productGroupId:"4",
         title:"High-heel mini platform boots",
         url:"https://www.storyly.io/",
         description:"",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-4/1-EBE5D5.jpg","https://random-feed-generator.vercel.app/images/clothes/group-4/2-EBE5D5.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#EBE5D5"),
             STRProductVariant(name:"size",value:"8,5")
         ]
     ),
     STRProductItem(
         productId:"35",
         productGroupId:"5",
         title:"Faux suede shirt",
         url:"https://www.storyly.io/",
         description:"Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-5/1-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/2-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/3-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/4-8092AA.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#8092AA"),
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"36",
         productGroupId:"5",
         title:"Faux suede shirt",
         url:"https://www.storyly.io/",
         description:"Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-5/1-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/2-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/3-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/4-8092AA.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#8092AA"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"37",
         productGroupId:"5",
         title:"Faux suede shirt",
         url:"https://www.storyly.io/",
         description:"Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-5/1-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/2-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/3-8092AA.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/4-8092AA.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#8092AA"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"38",
         productGroupId:"5",
         title:"Faux suede shirt",
         url:"https://www.storyly.io/",
         description:"Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-5/1-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/2-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/3-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/4-E6DFD7.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E6DFD7"),
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"39",
         productGroupId:"5",
         title:"Faux suede shirt",
         url:"https://www.storyly.io/",
         description:"Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-5/1-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/2-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/3-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/4-E6DFD7.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E6DFD7"),
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"40",
         productGroupId:"5",
         title:"Faux suede shirt",
         url:"https://www.storyly.io/",
         description:"Soft shirt constructed with high-quality suede fabric keeps the weather and makes it durable.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-5/1-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/2-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/3-E6DFD7.jpg","https://random-feed-generator.vercel.app/images/clothes/group-5/4-E6DFD7.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E6DFD7"),
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"41",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"34")
         ]
     ),
     STRProductItem(
         productId:"42",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"43",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"44",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"45",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"42")
         ]
     ),
     STRProductItem(
         productId:"46",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"44")
         ]
     ),
     STRProductItem(
         productId:"47",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-415A7E.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-415A7E.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#415A7E"),
             STRProductVariant(name:"size",value:"46")
         ]
     ),
     STRProductItem(
         productId:"48",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"34")
         ]
     ),
     STRProductItem(
         productId:"49",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"50",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"51",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"52",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"42")
         ]
     ),
     STRProductItem(
         productId:"53",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"44")
         ]
     ),
     STRProductItem(
         productId:"54",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-E3E7E8.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-E3E7E8.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#E3E7E8"),
             STRProductVariant(name:"size",value:"46")
         ]
     ),
     STRProductItem(
         productId:"55",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"34")
         ]
     ),
     STRProductItem(
         productId:"56",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"57",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"58",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"59",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"42")
         ]
     ),
     STRProductItem(
         productId:"60",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"44")
         ]
     ),
     STRProductItem(
         productId:"61",
         productGroupId:"6",
         title:"Super skinny jeans",
         url:"https://www.storyly.io/",
         description:"The super-stretchy denim with slim tapered ankle.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-6/1-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/2-7B7E83.jpg","https://random-feed-generator.vercel.app/images/clothes/group-6/3-7B7E83.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#7B7E83"),
             STRProductVariant(name:"size",value:"46")
         ]
     ),
     STRProductItem(
         productId:"62",
         productGroupId:"7",
         title:"Faded-effect denim corset top",
         url:"https://www.storyly.io/",
         description:"Straight-neck top with thin black straps.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"Small")
         ]
     ),
     STRProductItem(
         productId:"63",
         productGroupId:"7",
         title:"Faded-effect denim corset top",
         url:"https://www.storyly.io/",
         description:"Straight-neck top with thin black straps.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"Medium")
         ]
     ),
     STRProductItem(
         productId:"64",
         productGroupId:"7",
         title:"Faded-effect denim corset top",
         url:"https://www.storyly.io/",
         description:"Straight-neck top with thin black straps.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"Large")
         ]
     ),
     STRProductItem(
         productId:"65",
         productGroupId:"7",
         title:"Faded-effect denim corset top",
         url:"https://www.storyly.io/",
         description:"Straight-neck top with thin black straps.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-7/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-7/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"X-Large")
         ]
     ),
     STRProductItem(
         productId:"66",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFFFFF"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"67",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFC0CB"),
             STRProductVariant(name:"size",value:"37")
         ]
     ),
     STRProductItem(
         productId:"68",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFC0CB"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"69",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFC0CB"),
             STRProductVariant(name:"size",value:"39")
         ]
     ),
     STRProductItem(
         productId:"70",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-pink.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-pink.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFC0CB"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"71",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#C2B280"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"72",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#C2B280"),
             STRProductVariant(name:"size",value:"37")
         ]
     ),
     STRProductItem(
         productId:"73",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#C2B280"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"74",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#C2B280"),
             STRProductVariant(name:"size",value:"39")
         ]
     ),
     STRProductItem(
         productId:"75",
         productGroupId:"8",
         title:"Heeled slingback strap shoes",
         url:"https://www.storyly.io/",
         description:"Heel height: 10cm. AIRFIT®. FLEXIBLE TECHNICAL LATEX FOAM INSOLE, DESIGNED TO OFFER GREATER COMFORT. Slingback stiletto heel shoes. Buckled ankle strap fastening. Pointed toe.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-8/1-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/2-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/3-ecru.jpg","https://random-feed-generator.vercel.app/images/clothes/group-8/4-ecru.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#C2B280"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"76",
         productGroupId:"9",
         title:"Striped sweatshirt with zip",
         url:"https://www.storyly.io/",
         description:"An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"XS")
         ]
     ),
     STRProductItem(
         productId:"77",
         productGroupId:"9",
         title:"Striped sweatshirt with zip",
         url:"https://www.storyly.io/",
         description:"An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"S")
         ]
     ),
     STRProductItem(
         productId:"78",
         productGroupId:"9",
         title:"Striped sweatshirt with zip",
         url:"https://www.storyly.io/",
         description:"An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"M")
         ]
     ),
     STRProductItem(
         productId:"79",
         productGroupId:"9",
         title:"Striped sweatshirt with zip",
         url:"https://www.storyly.io/",
         description:"An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"L")
         ]
     ),
     STRProductItem(
         productId:"80",
         productGroupId:"9",
         title:"Striped sweatshirt with zip",
         url:"https://www.storyly.io/",
         description:"An easy-to-wear casual sweatshirt you'll want to wear time and time again you need to look no further.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-9/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/3.jpg","https://random-feed-generator.vercel.app/images/clothes/group-9/4.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"XL")
         ]
     ),
     STRProductItem(
         productId:"81",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFFDD0"),
             STRProductVariant(name:"size",value:"34")
         ]
     ),
     STRProductItem(
         productId:"82",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFFDD0"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"83",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFFDD0"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"84",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFFDD0"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"85",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-cream.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/4-cream.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#FFFDD0"),
             STRProductVariant(name:"size",value:"42")
         ]
     ),
     STRProductItem(
         productId:"86",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#808080"),
             STRProductVariant(name:"size",value:"34")
         ]
     ),
     STRProductItem(
         productId:"87",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#808080"),
             STRProductVariant(name:"size",value:"36")
         ]
     ),
     STRProductItem(
         productId:"88",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#808080"),
             STRProductVariant(name:"size",value:"38")
         ]
     ),
     STRProductItem(
         productId:"89",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#808080"),
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"90",
         productGroupId:"10",
         title:"Wide-leg cargo jeans",
         url:"https://www.storyly.io/",
         description:"Made from super soft stretch denim with a high rise waist and utility pocket styling.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-10/1-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/2-gray.jpg","https://random-feed-generator.vercel.app/images/clothes/group-10/3-gray.jpg"],
         variants:[
             STRProductVariant(name:"color",value:"#808080"),
             STRProductVariant(name:"size",value:"42")
         ]
     ),
     STRProductItem(
         productId:"91",
         productGroupId:"11",
         title:"Men's basic sneakers",
         url:"https://www.storyly.io/",
         description:"White basic trainers. Tag detail on the laces.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-11/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-11/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-11/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"40")
         ]
     ),
     STRProductItem(
         productId:"92",
         productGroupId:"11",
         title:"Men's basic sneakers",
         url:"https://www.storyly.io/",
         description:"White basic trainers. Tag detail on the laces.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-11/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-11/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-11/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"41")
         ]
     ),
     STRProductItem(
         productId:"93",
         productGroupId:"11",
         title:"Men's basic sneakers",
         url:"https://www.storyly.io/",
         description:"White basic trainers. Tag detail on the laces.",
         price: 25.99, salesPrice: 25.99,
         currency:"USD",
         
         imageUrls:["https://random-feed-generator.vercel.app/images/clothes/group-11/1.jpg","https://random-feed-generator.vercel.app/images/clothes/group-11/2.jpg","https://random-feed-generator.vercel.app/images/clothes/group-11/3.jpg"],
         variants:[
             STRProductVariant(name:"size",value:"42")
         ]
     ),
 ]


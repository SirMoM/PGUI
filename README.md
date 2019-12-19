# PGUI
A GUI for Processing

Language English

Background Task?

# Class
* Util
    * AABB ==> Mouse over / click in usw
        Eigene Klasse oder nur eine Funktion
* GUIMANAGER
* View
* Component
    * Label
    * ClickableComponent
        * KeyboardInputComponent
            * TextInput
            * DateInput
            * NumberInput
        * Button
        * DropDownMenu
        * RadioButton
    * Container
        * ListContainer
        * GridContainer
        * WindowContainer
    * Animation
        * Toast
        * BlendIn
        * BlendOut
        * Move?
* Input
    * UserInput
        * MouseInput
        * KeyboardInput
    * ExternalInput
        * WindowResize
        * RefreshView
* AnimationTimer
* ViewTransition == Speichter View wechsel
* Controller ?


# Interface
* Action 

# GUIMANAGER 
Hält alle Views 
Übergänge zwichen den Views
Leitet alle Events weiter
Verwaltet Focus
Gibt FPS an 
AnimationTimer registriert sich im GUIMANAGER ==> dieser tickt den AnimationTimer hoch

GUIMANAGER hält VIEWS 
VIEWS halten COMPONENTS

### VARIABLES
* Set<String> viewNames; //  NEEDED TO BE SURE THAT THERE ARE NOT 2 VIEWS WITH THE SAME NAME?
* Set<ViewTransition> viewTransitions // muss nicht gehalten werden
* List<View> views
* List<AnimationTimer> animationTimers
* View currentView;

### FUNCTIONS
* registerView(View view)
* goToView(final String viewName)

# COMPONENTS
### VARIABLES
* int xPos
* int yPos
* View view

### FUNCTIONS
* void draw(void)

## ClickableComponent
### FUNCTIONS
* void onMouseInput(MouseInput)

## KeyboardInputComponent
### FUNCTIONS
* void onKeyboardInput(KeyboardInput)


# View
### VARIABLES
* final String name


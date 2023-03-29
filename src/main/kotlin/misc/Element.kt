package main.kotlin.misc

/**
 * Holds information of the periodic table of elements.
 *
 * @author Felix Divo
 */
enum class Element(
    /**
     * Get the full name of this Element
     *
     * @return this Element's full name (not the symbol)
     */
    val fullName: String,
    /**
     * Get the atomic mass of this Element.
     *
     * @return this Element's average atomic mass in 'u'
     */
    val atomicMass: Float,
    /**
     * get the electron negativity of this Element.
     *
     * @return this Element's electron negativity in Pauling
     */
    val electroNegativity: Float
) {
    /* SYMBOL NAME ATOMIC_WEIGHT ELECTRONEGATIVITY */
    H("Hydrogen", 1.00794f, 2.1f),
    He("Helium", 4.0026f, 0f),
    Li("Lithium", 6.941f, 0.98f),
    Be("Beryllium", 9.01218f, 1.57f),
    B("Boron", 10.811f, 2.04f),
    C("Carbon", 12.011f, 2.55f),
    N("Nitrogen", 14.0067f, 3.04f),
    O("Oxygen", 15.9994f, 3.44f),
    F("Fluorine", 18.9984f, 3.98f),
    Ne("Neon", 20.1797f, 0f),
    Na("Sodium", 22.98977f, 0.93f),
    Mg("Magnesium", 24.305f, 1.31f),
    Al("Aluminum", 26.98154f, 1.61f),
    Si("Silicon", 28.0855f, 1.9f),
    P("Phosphorus", 30.97376f, 2.19f),
    S("Sulfur", 32.066f, 2.58f),
    Cl("Chlorine", 35.4527f, 3.16f),
    Ar("Argon", 39.948f, 0f),
    K("Potassium", 39.0983f, 0.82f),
    Ca("Calcium", 40.078f, 1f),
    Sc("Scandium", 44.9559f, 1.36f),
    Ti("Titanium", 47.88f, 1.54f),
    V("Vanadium", 50.9415f, 1.63f),
    Cr("Chromium", 51.996f, 1.66f),
    Mn("Manganese", 54.938f, 1.55f),
    Fe("Iron", 55.847f, 1.83f),
    Co("Cobalt", 58.9332f, 1.88f),
    Ni("Nickel", 58.6934f, 1.91f),
    Cu("Copper", 63.546f, 1.9f),
    Zn("Zinc", 65.39f, 1.65f),
    Ga("Gallium", 69.723f, 1.81f),
    Ge("Germanium", 72.61f, 2.01f),
    As("Arsenic", 74.9216f, 2.18f),
    Se("Selenium", 78.96f, 2.55f),
    Br("Bromine", 79.904f, 2.96f),
    Kr("Krypton", 83.8f, 0f),
    Rb("Rubidium", 85.4678f, 0.82f),
    Sr("Strontium", 87.62f, 0.95f),
    Y("Yttrium", 88.9059f, 1.22f),
    Zr("Zirconium", 91.224f, 1.33f),
    Nb("Niobium", 92.9064f, 1.6f),
    Mo("Molybdenum", 95.94f, 2.16f),
    Tc("Technetium", 98f, 1.9f),
    Ru("Ruthenium", 101.07f, 2.2f),
    Rh("Rhodium", 102.9055f, 2.28f),
    Pd("Palladium", 106.42f, 2.2f),
    Ag("Silver", 107.868f, 1.93f),
    Cd("Cadmium", 112.41f, 1.69f),
    In("Indium", 114.82f, 1.78f),
    Sn("Tin", 118.71f, 1.96f),
    Sb("Antimony", 121.757f, 2.05f),
    Te("Tellurium", 127.6f, 2.1f),
    I("Iodine", 126.9045f, 2.66f),
    Xe("Xenon", 131.29f, 2.6f),
    Cs("Cesium", 132.9054f, 0.79f),
    Ba("Barium", 137.33f, 0.89f),
    La("Lanthanum", 138.9055f, 1.1f),
    Ce("Cerium", 140.12f, 1.12f),
    Pr("Praseodymium", 140.9077f, 1.13f),
    Nd("Neodymium", 144.24f, 1.14f),
    Pm("Promethium", 145f, 1.13f),
    Sm("Samarium", 150.36f, 1.17f),
    Eu("Europium", 151.965f, 1.2f),
    Gd("Gadolinium", 157.25f, 1.2f),
    Tb("Terbium", 158.9253f, 1.1f),
    Dy("Dysprosium", 162.5f, 1.22f),
    Ho("Holmium", 164.9303f, 1.23f),
    Er("Erbium", 167.26f, 1.24f),
    Tm("Thulium", 168.9342f, 1.25f),
    Yb("Ytterbium", 173.04f, 1.1f),
    Lu("Lutetium", 174.967f, 1.27f),
    Hf("Hafnium", 178.49f, 1.3f),
    Ta("Tantalum", 180.9479f, 1.5f),
    W("Tungsten", 183.85f, 2.36f),
    Re("Rhenium", 186.207f, 1.9f),
    Os("Osmium", 190.2f, 2.2f),
    Ir("Iridium", 192.22f, 2.2f),
    Pt("Platinum", 195.08f, 2.28f),
    Au("Gold", 196.9665f, 2.54f),
    Hg("Mercury", 200.59f, 2f),
    Tl("Thallium", 204.383f, 2.04f),
    Pb("Lead", 207.2f, 2.33f),
    Bi("Bismuth", 208.9804f, 2.02f),
    Po("Polonium", 209f, 2f),
    At("Astatine", 210f, 2.2f),
    Rn("Radon", 222f, 0f),
    Fr("Francium", 223f, 0.7f),
    Ra("Radium", 226.0254f, 0.89f),
    Ac("Actinium", 227f, 1.1f),
    Th("Thorium", 232.0381f, 1.3f),
    Pa("Protactinium", 231.0359f, 1.5f),
    U("Uranium", 238.029f, 1.38f),
    Np("Neptunium", 237.0482f, 1.36f),
    Pu("Plutonium", 244f, 1.28f),
    Am("Americium", 243f, 1.3f),
    Cm("Curium", 247f, 1.3f),
    Bk("Berkelium", 247f, 1.3f),
    Cf("Californium", 251f, 1.3f),
    Es("Einsteinium", 252f, 1.3f),
    Fm("Fermium", 257f, 1.3f),
    Md("Mendelevium", 258f, 1.3f),
    No("Nobelium", 259f, 1.3f),
    Lr("Lawrencium", 262f, 0f),
    Rf("Rutherfordium", 261f, 0f),
    Db("Dubnium", 262f, 0f),
    Sg("Seaborgium", 263f, 0f),
    Bh("Bohrium", 262f, 0f),
    Hs("Hassium", 265f, 0f),
    Mt("Meitnerium", 266f, 0f),
    Ds("Darmstadtium", 269f, 0f),
    Rg("Roentgenium", 272f, 0f),
    Cn("Copernicium", 277f, 0f),
    Nh("Nihonium", 287f, 0f),
    Fl("Flerovium", 289f, 0f),
    Mc("Moskovium", 288f, 0f),
    Lv("Livermorium", 289f, 0f),
    Ts("Tennesine", 291f, 0f),
    Og("Oganesson", 293f, 0f);

    /**
     * Holds mappings of the Elements.
     *
     *
     * There's a bit of java kung fu going on here that deserves an explanation.
     * The map is put inside a static inner (holder) class, so it gets
     * initialized before the enum instances are initialized, that way they can
     * add themselves to it. If not in the inner static class, it would not be
     * initialized, because the first thing initialized in the enum class must be
     * the instances, but static inner classes are initialized before the class
     * is initialized. (Trick from [here](http://stackoverflow.com/a/22738610/2210921).)
     */
    internal object Holder {
        /** Mapps the atomic number to the Element  */
        val map_atomicNumber: MutableMap<Int, Element> = HashMap()

        /** Mapps the symbol to the Element  */
        val map_symbol: MutableMap<String, Element> = HashMap()
    }

    /**
     * Creates a new Element and mapps it to 'Holder.map_atomicNumber' and
     * 'Holder.map_symbol'.
     *
     * @param fullName
     * the full name of the element (not the symbol)
     * @param atomicMass
     * the average atomic mass of the element in 'u'
     * @param electroNegativity
     * the electron negativity by Pauling
     */
    init {
        Holder.map_atomicNumber[atomicNumber] = this
        Holder.map_symbol[name] = this
    }

    /**
     * Get the atomic number of this Element.
     *
     * @return this Element's atomic number in 'u'
     */
    val atomicNumber: Int
        get() = ordinal + 1

    /**
     * Get the symbol of this Element.
     *
     * @return this Element's symbol (1 to 3 chars long)
     */
    fun getSymbol(): String {
        return name
    }

    /**
     * Get a String representation of this Element.
     *
     *
     * For example:<br></br>
     * *He[fullName="Helium", atomicMass="4.0026", electroNegativity="0"]*
     *
     * @return a String representation of this Element.
     */
    override fun toString(): String {
        return (name + "[fullName=\"" + fullName + "\", atomicMass=\""
                + atomicMass + "\", electroNegativity=\""
                + electroNegativity + "\"]")
    }

    companion object {
        /**
         * Search for the Element by the given atomic number.
         *
         * @param atomicNumber
         * ths atomic number to search by
         *
         * @return the Element with the given atomic number or null if it couldn't
         * be found
         */
        fun getByAtomicNumber(atomicNumber: Int): Element? {
            return Holder.map_atomicNumber[atomicNumber]
        }

        /**
         * Search for the Element by the given symbol.
         *
         * @param symbol
         * ths symbol to search by, must be correctly typed (case-
         * sensitive)
         *
         * @return the Element with the given symbol or null if it couldn't be found
         *
         * @see {@link .toCorrectSymbol
         */
        fun getBySymbol(symbol: String): Element? {
            return Holder.map_symbol[symbol]
        }

        /**
         * Get the symbol of the Elemment with the specified atomic number.
         *
         * @param atomicNumber
         * ths atomic number to search by
         *
         * @return the element's symbol or null if the element couldn't be found
         */
        fun getSymbolByAtomicNumber(atomicNumber: Int): String? {
            val e = Holder.map_atomicNumber[atomicNumber]
            return e?.getSymbol()
        }

        /**
         * Get the atomic number of the Elemment with the specified symbol.
         *
         * @param symbol
         * ths symbol to search by, must be correctly typed (case-
         * sensitive)
         *
         * @return the element's atomic number or 0 if the element couldn't be found
         *
         * @see {@link .toCorrectSymbol
         */
        fun getAtomicNumberBySymbol(symbol: String): Int {
            val e = Holder.map_symbol[symbol]
            return e?.atomicNumber ?: 0
        }

        /**
         * Finds out, whether an Element with the given symbol exists.
         *
         * @param symbol
         * the symbol to search by, must be correctly typed (case-
         * sensitive)
         *
         * @return true, if an Element with the given symbol exists.
         *
         * @see {@link .exists
         * @see {@link .toCorrectSymbol
         */
        fun exists(symbol: String): Boolean {
            return null != Holder.map_symbol[symbol]
        }

        /**
         * Finds out, whether an Element with the given atomic number exists.
         *
         * @param atomicNumber
         * the atomic number to search by
         *
         * @return true, if an Element with the given atomic number.
         *
         * @see {@link .exists
         */
        fun exists(atomicNumber: Int): Boolean {
            return null != Holder.map_atomicNumber[atomicNumber]
        }

        /**
         * Turns a, for example, lowercase Symbol ("cl") into the correctly spelled
         * symbol ("Cl"). Assumes that the symbol corresponds to an existing element
         * of the periodic table of elements.
         *
         *
         * Examples:<br></br>
         * "cl" -> "Cl"<br></br>
         * "Cl" -> "Cl"<br></br>
         * "cL" -> "Cl"<br></br>
         * "CL" -> "Cl"<br></br>
         *
         * @param symbol
         * the symbol to convert (1 to 3 chars long)
         *
         * @return the correctly spelled symbol
         */
        fun toCorrectSymbol(symbol: String): String {
            var correctSymbol = ""
            correctSymbol += symbol[0].uppercaseChar()
            if (2 == symbol.length) {
                correctSymbol += symbol[1].lowercaseChar()
            } else if (3 == symbol.length) {
                correctSymbol += symbol[1].lowercaseChar()
                correctSymbol += symbol[2].lowercaseChar()
            }
            return correctSymbol
        }
    }
}